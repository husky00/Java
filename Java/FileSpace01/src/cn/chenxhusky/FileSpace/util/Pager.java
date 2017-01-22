package cn.chenxhusky.FileSpace.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户分页处理
 * @author husky
 *
 */
public class Pager {

	private Integer currPage;							//当前页			
	private Integer ItemOfPage;							//每一页显示的
	private Integer totalItem;							//总共 的纪录条数
	private Integer totalPage;							//总共 的页数
	private List<Object> items=new ArrayList<Object>();	//当前页的纪录列表
	
	
	public Pager(){
		this.currPage=1;
		this.ItemOfPage=10;
		this.totalItem=0;
		this.totalPage=0;
	}


	public Integer getCurrPage() {
		return currPage;
	}


	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}


	public Integer getItemOfPage() {
		return ItemOfPage;
	}


	public void setItemOfPage(Integer itemOfPage) {
		ItemOfPage = itemOfPage;
	}


	public Integer getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}


	public Integer getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public List<Object> getItems() {
		return items;
	}


	public void setItems(List<Object> items) {
		this.items = items;
	}
	
	
}
