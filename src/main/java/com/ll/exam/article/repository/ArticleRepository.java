package com.ll.exam.article.repository;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Repository;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.mymap.MyMap;
import com.ll.exam.mymap.SecSql;

import java.util.List;

@Repository
public class ArticleRepository {
    @Autowired
    private MyMap myMap;

    public List<ArticleDto> getArticles() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("ORDER BY id DESC");
        return sql.selectRows(ArticleDto.class);
    }

    public ArticleDto findById(int id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("where id = %d".formatted(id));

        return sql.selectRow(ArticleDto.class);
    }

    public long getArticlesCnt() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT COUNT(*)")
                .append("FROM article");

        return sql.selectLong();
    }

    public long write(String title, String body, boolean isBlind) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO article")
                .append("SET createdDate = NOW()")
                .append(", modifiedDate = NOW()")
                .append(", title = ?", title)
                .append(", body = ?", body)
                .append(", isBlind = ?", isBlind);

        return sql.insert();
    }

    public long modify(int id, String title, String body, boolean isBlind) {
        ArticleDto articleDto = findById(id);

        if(articleDto == null) {
            return 0;
        }

        SecSql sql = myMap.genSecSql();

        sql
                .append("UPDATE article")
                .append("SET title = ?", title)
                .append(", body = ?", body)
                .append(", isBlind = ?", isBlind)
                .append(", modifiedDate = NOW()")
                .append("WHERE id = %d".formatted(articleDto.getId()));

        articleDto.setTitle(title);
        articleDto.setBody(body);
        articleDto.setBlind(isBlind);

        long affectedRowCount = sql.update();

        return affectedRowCount;
    }

    public void delete(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("DELETE FROM article")
                .append("WHERE id = ?", id);

        sql.update();
    }

    public ArticleDto getPrevArticle(int id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("where id < %d".formatted(id))
                .append("and isBlind = false")
                .append("order by id desc")
                .append("limit 1");

        return sql.selectRow(ArticleDto.class);
    }

    public ArticleDto getNextArticle(int id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("where id > %d".formatted(id))
                .append("and isBlind != 1")
                .append("order by id")
                .append("limit 1");

        return sql.selectRow(ArticleDto.class);
    }
}
