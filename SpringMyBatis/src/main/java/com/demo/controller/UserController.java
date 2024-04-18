package com.demo.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.model.User;
import com.demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	/*
	 * 用户列表与分页Action
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNO) {
		int size = 5;
		model.addAttribute("size", size);
		/**model.addAttribute("num", userService.queryUserCountCurrentPageNO(pageNO)); */
		model.addAttribute("num", userService.queryUserCountPageNO(size, pageNO));
		model.addAttribute("count", userService.queryUserCount());
		model.addAttribute("userList", userService.queryUserPager_1(pageNO, size));
		return "user/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}

	/**
	 * 添加用户保存
	 * 
	 * @param model
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model, @ModelAttribute("user") @Valid User user) {
		userService.insertUser(user);

		return "redirect:/user/list";
	}

	/**
	 * 添加用户保存
	 * 
	 * @param model
	 * @param entity
	 * @param bindingResult
	 * @return
	 */
	/**
	 * @RequestMapping("/save1") public String save1(Model model, HttpServletRequest
	 * request) {
	 * 
	 * User user = new User();
	 * 
	 * user.setUser_name(request.getParameter("user_name"));
	 * 
	 * userService.insertUser(user);
	 * 
	 * return "redirect:/user/list"; }
	 * 
	 * }
	 */
	/**
	 * 编辑用户
	 * 
	 * @param model
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/edit/{user_id}")
	public String edit(Model model, @PathVariable int user_id) {

		model.addAttribute("user", userService.queryUserById(user_id));
		return "user/edit";
	}

	/**
	 * 修改用户并保存
	 * 
	 * @param model
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/editSave")
	public String editSave(Model model, @ModelAttribute("user") @Valid User user) {
		userService.updateUser(user);
		return "redirect:/user/list";
	}

	/**
	 * 根据用户id删除用户
	 * 
	 * @param model
	 * @param user_id
	 * @param pageNO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/deleteUserById/{user_id}")
	public String deleteUserById(Model model, @PathVariable int user_id,
			@RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {

		if (userService.deleteUserById(user_id) > 0) {
			redirectAttributes.addFlashAttribute("message", "删除成功！");
		} else {
			redirectAttributes.addFlashAttribute("message", "删除失败！");
		}
		return "redirect:/user/list?pageNO=" + pageNO;

	}

	/**
	 * 删除多个用户
	 * 
	 * @param model
	 * @param userIds
	 * @param pageNO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/deleteUsers")
	public String deleteUsers(Model model, @RequestParam int[] userId,
			@RequestParam(required = false, defaultValue = "1") int pageNO, RedirectAttributes redirectAttributes) {

		if (userService.deleteUsers(userId) > 0) {
			redirectAttributes.addFlashAttribute("message", "删除成功！");
		} else {
			redirectAttributes.addFlashAttribute("message", "删除失败！");
		}
		return "redirect:/user/list?pageNO=" + pageNO;
	}
}
