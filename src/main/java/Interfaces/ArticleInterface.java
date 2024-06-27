package Interfaces;

import Models.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleInterface<T> {

        void add(T t);
        List<T> getAll();
        void update(T t) throws SQLException;


        List<Article> getByIndex();

        void updateArticle(Article article) throws SQLException;
}
