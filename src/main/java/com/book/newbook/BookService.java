package com.book.newbook;

//import com.example.demo.Indices;
//import com.example.demo.SearchUtil;
//import com.book.sandy.document.Book;
//import com.book.sandy.helper.indices;
//import com.book.sandy.search.SearchRequestDTO;
//import com.book.sandy.search.util.SearchUtil;
//import com.book.sandy.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

	private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    private final RestHighLevelClient client;

    @Autowired
    public BookService(RestHighLevelClient client) {
        this.client = client;
    }
    
    
    public List<Book> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                indices.BOOK_INDEX,
                dto
        );

        return searchInternal(request);
    }
    
    
    private List<Book> searchInternal(final SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Book> books = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                books.add(
                        MAPPER.readValue(hit.getSourceAsString(), Book.class)
                );
            }

            return books;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    public List<Book> getAllBooksCreatedSince(final Date date) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                indices.BOOK_INDEX,
                "created",
                date
        );

        return searchInternal(request);
    }

    public List<Book> searchCreatedSince(final SearchRequestDTO dto, final Date date) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                indices.BOOK_INDEX,
                dto,
                date
        );

        return searchInternal(request);
    }
    
    
    public Boolean index(final Book book) {
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(book);

            final IndexRequest request = new IndexRequest(indices.BOOK_INDEX);
            request.id(book.getId());
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
    
    public Book getById(final String vehicleId) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(indices.BOOK_INDEX, vehicleId),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), Book.class);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }


	public String deleteBook(String id) {
		// TODO Auto-generated method stub
		//return null;
		 try {
	            final DeleteResponse documentFields = client.delete(
	                    new DeleteRequest(indices.BOOK_INDEX, id),
	                    RequestOptions.DEFAULT
	            );
	           // if (documentFields == null || documentFields.isSourceEmpty()) {
	             //   return null;
	            //}
	            return documentFields.getId();
	           // return MAPPER.readValue(documentFields.getSourceAsString(), Train.class);
	        } catch (final Exception e) {
	            LOG.error(e.getMessage(), e);
	            return null;
	        }
	}


	public List<Book> getByAuthor(String name) {
		// TODO Auto-generated method stub
		//return null;
		 final SearchRequest request = SearchUtil.buildSearchRequest(
	                indices.BOOK_INDEX,
	                "name",
	                 name
	        );

	        return searchInternal(request);
		
		
		 /* QueryBuilder queryBuilder = QueryBuilders
			      .matchQuery("name", name);

			    Query searchQuery = new NativeSearchQueryBuilder()
			      .withQuery(queryBuilder)
			      .build();*/

			 /* SearchHits<Book> productHits = 
			      elasticsearchOperations
			      .search(searchQuery, 
			          Product.class,
			          IndexCoordinates.of(PRODUCT_INDEX));
			    final SearchHit[] searchHits = response.getHits().getHits();
	            final List<Book> books = new ArrayList<>(searchHits.length);
	            for (SearchHit hit : searchHits) {
	                books.add(
	                        MAPPER.readValue(hit.getSourceAsString(), Book.class)
	                );
	            }

	            return books;*/
		
	}


	public List<Book> getAll() {
		// TODO Auto-generated method stub
		//return null;
		 final SearchRequest request = SearchUtil.buildSearchRequest(
				 indices.BOOK_INDEX);
		  return searchInternal(request);
	}
}
