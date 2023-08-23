package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public void createArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        article.setBody(articleDto.getBody());
        article.setName(articleDto.getName());
        article.setCategory(articleDto.getCategory());

        this.articleRepository.save(article);
    }

    @PatchMapping(path = "/{id}")
    public void patchArticle(@PathVariable long id, @RequestBody ArticleDto articleDto) {
        Article article = this.articleRepository.findById(id);
        article.setCategory(articleDto.getCategory());
        article.setBody(articleDto.getBody());
        article.setName(articleDto.getName());
        article.setId(articleDto.getId());
        this.articleRepository.save(article);
    }

    @GetMapping(path = "/{id}")
    public Article getArticleById(@PathVariable long id) {
        Article article = this.articleRepository.findById(id);
        return article;
    }

    // END
}