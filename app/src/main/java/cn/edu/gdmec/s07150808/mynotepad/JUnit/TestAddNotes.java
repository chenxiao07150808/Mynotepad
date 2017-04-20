package cn.edu.gdmec.s07150808.mynotepad.JUnit;


import android.test.AndroidTestCase;

import cn.edu.gdmec.s07150808.mynotepad.dbservice.UserService;
import cn.edu.gdmec.s07150808.mynotepad.entity.ListViewEntity;

public class TestAddNotes extends AndroidTestCase {

	private UserService service;
	public void testaddNotes(){
		service=new UserService(this.getContext());
		ListViewEntity ete = new ListViewEntity(
				"今天", "内容","djsks");
		service.insertNote(ete);

	}
}
