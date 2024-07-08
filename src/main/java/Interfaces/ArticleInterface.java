package Interfaces;

import Models.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleInterface<T> {

        void addArticle(T t)throws SQLException ;

        Article getArticleById(Long id);

        void deleteArticle(long id) throws SQLException;

        List<T> getAllArticles() throws SQLException;
        void updateArticle(T t) throws SQLException;

        List<Article> getByIndex();

        List<Article> getByIndex(Long id);

        void rateArticle(Long articleId, int rating) throws SQLException;
        double getAverageRating(Long articleId);
        void updateArticle(Article article) throws SQLException;
}
