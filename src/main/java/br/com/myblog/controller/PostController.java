package br.com.myblog.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.myblog.controller.dto.FormDto;
import br.com.myblog.model.Categoria;
import br.com.myblog.model.Post;
import br.com.myblog.repository.CategoriaRepository;
import br.com.myblog.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService service;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/")
	public String index() {
		return "redirect:/posts";
	}

	@GetMapping("/posts")
	public ModelAndView index(@RequestParam(required = false) String category,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		ModelAndView view = new ModelAndView("index");
		Page<Post> posts;

		if (category != null) {
			Categoria categoria = categoriaRepository.findByNome(category);
			posts = service.findByCategory(categoria, paginacao);
		} else {
			posts = service.findAll(paginacao);
		}

		List<Integer> pageNumbers = totalPages(posts);

		posts.getTotalPages();

		view.addObject("posts", posts);
		view.addObject("pageNumbers", pageNumbers);

		return view;
	}

	@GetMapping("/posts/{id}")
	public ModelAndView postDetails(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("postDetalhes");
		Optional<Post> post = service.findById(id);

		if (post.isPresent())
			view.addObject("post", post.get());

		return view;

	}

	@GetMapping("/newpost")
	public String newPostForm() {

		return "postForm";
	}

	@PostMapping("/newpost")
	public String saveNewPost(FormDto dto, RedirectAttributes attributes) {
		Post post = FormDto.converter(dto, categoriaRepository);
		service.insert(post);

		attributes.addFlashAttribute("sucesso", "Post efetuado!");

		return "redirect:/posts";
	}

	private List<Integer> totalPages(Page<Post> posts) {

		int totalPages = posts.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		return pageNumbers;
	}

}
