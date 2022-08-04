package com.ll.exam.article.service;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Service;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleDto> getArticles() {
        return articleRepository.getArticles();
    }

    public ArticleDto findById(int id) {
        return articleRepository.findById(id);
    }

    public long getArticlesCnt() {
        return articleRepository.getArticlesCnt();
    }

    public long write(String title, String body, boolean isBlind) {
        return articleRepository.write(title, body, isBlind);
    }

    public long modify(int id, String title, String body, boolean isBlind) {
        return articleRepository.modify(id, title, body, isBlind);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }

    public ArticleDto getPrevArticle(int id) {
        return articleRepository.getPrevArticle(id);
    }

    public ArticleDto getNextArticle(int id) {
        return articleRepository.getNextArticle(id);
    }
}
