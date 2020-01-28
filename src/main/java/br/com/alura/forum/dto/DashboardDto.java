package br.com.alura.forum.dto;

import java.util.List;

import br.com.alura.forum.domain.Category;

public class DashboardDto {

	private String categoryName;
	private List<String> subcategories;
	private int allTopics;
	private int lastWeekTopics;
	private int unAnsweredTopics;

	public DashboardDto(Category category, int topicos, int lastWeekTopics, int unAnsweredTopics) {
		this.subcategories = category.getSubcategoryNames();
		this.categoryName = category.getName();
		this.allTopics = topicos;
		this.lastWeekTopics = lastWeekTopics;
		this.unAnsweredTopics = unAnsweredTopics;

	}

	public String getCategoryName() {
		return categoryName;
	}


	public Integer getAllTopics() {
		return allTopics;
	}

	public void setAllTopics(Integer allTopics) {
		this.allTopics = allTopics;
	}

	public Integer getLastWeekTopics() {
		return lastWeekTopics;
	}

	public void setLastWeekTopics(Integer lastWeekTopics) {
		this.lastWeekTopics = lastWeekTopics;
	}

	public Integer getUnAnsweredTopics() {
		return unAnsweredTopics;
	}

	public void setUnAnsweredTopics(Integer unAnsweredTopics) {
		this.unAnsweredTopics = unAnsweredTopics;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setAllTopics(int allTopics) {
		this.allTopics = allTopics;
	}

	public void setLastWeekTopics(int lastWeekTopics) {
		this.lastWeekTopics = lastWeekTopics;
	}

	public void setUnAnsweredTopics(int unAnsweredTopics) {
		this.unAnsweredTopics = unAnsweredTopics;
	}
}
