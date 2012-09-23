package io.searchbox.jest.sample.service;

import io.searchbox.client.ElasticSearchClient;
import io.searchbox.client.ElasticSearchResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.jest.sample.model.Article;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ferhat
 */
@Service
public class SearchService {

    @Autowired
    ElasticSearchClient elasticSearchClient;


    public void indexSampleArticles() {

        Article article1 = new Article();
        article1.setId(1L);
        article1.setAuthor("Robert Anthony Salvatore");
        article1.setContent("Homeland follows the story of Drizzt from around the time and circumstances of his birth and his upbringing amongst the drow (dark elves). " +
                "The book takes the reader into Menzoberranzan, the drow home city. From here, the reader follows Drizzt on his quest to follow his principles in a land where such " +
                "feelings are threatened by all his family including his mother Matron Malice. In an essence, the book introduces Drizzt Do'Urden," +
                " one of Salvatore's more famous characters from the Icewind Dale Trilogy.");

        Article article2 = new Article();
        article2.setId(2L);
        article2.setAuthor("John Ronald Reuel Tolkien");
        article2.setContent("The Lord of the Rings is an epic high fantasy novel written by English philologist and University of Oxford professor J. R. R. Tolkien. " +
                "The story began as a sequel to Tolkien's 1937 children's fantasy novel The Hobbit, but eventually developed into a much larger work. " +
                "It was written in stages between 1937 and 1949, much of it during World War II.[1] It is the third best-selling novel ever written, with over 150 million copies sold");

        try {
            // Delete articles index if it is exists
            DeleteIndex deleteIndex = new DeleteIndex("articles");
            elasticSearchClient.execute(deleteIndex);

            // Create articles index
            CreateIndex createIndex = new CreateIndex("articles");
            elasticSearchClient.execute(createIndex);

            /**
             *  if you don't want to use bulk api use below code in a loop.
             *
             *  Index index = new Index.Builder(article2).build();
             *  elasticSearchClient.execute(index);
             *
             */

            // Bulk operations does not uses default indices yet, we pass them while constructing bulk!
            Bulk bulk = new Bulk("articles", "article");
            bulk.addIndex(new Index.Builder(article1).build());
            bulk.addIndex(new Index.Builder(article2).build());


            elasticSearchClient.execute(bulk);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Article> searchArticles(String param) {

        try {
            QueryBuilder queryBuilder = QueryBuilders.queryString(param);

            Search search = new Search(queryBuilder);
            search.addIndex("articles");
            search.addType("article");

            ElasticSearchResult result = elasticSearchClient.execute(search);
            return result.getSourceAsObjectList(Article.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
