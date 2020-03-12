package de.uhh.hop.JavaCI.unit;

import de.uhh.hop.JavaCI.model.TodoEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TodoEntryTest
{
	private TodoEntry entryA;
	private TodoEntry entryB;
	private TodoEntry entryC;

	@Before
	public void initTest() {
		 entryA = new TodoEntry("testTitleA", "testTextA");
		 entryB = new TodoEntry("testTitleB", "testTextB");
		 entryC = new TodoEntry("testTitleC", "testTextC");
	}

	@Test
	public void testGetId()
	{
		Assert.assertNotNull(entryA.getId());
		Assert.assertNotEquals(entryA.getId(), entryB.getId());
	}

	@Test
	public void testGetSetTitle() {
		Assert.assertEquals("testTitleA", entryA.getTitle());
		Assert.assertEquals("testTitleB", entryB.getTitle());
		Assert.assertEquals("testTitleC", entryC.getTitle());

		entryA.setTitle("testTitleANew");
		entryB.setTitle("testTitleBNew");
		entryC.setTitle("testTitleCNew");

		Assert.assertEquals("testTitleANew", entryA.getTitle());
		Assert.assertEquals("testTitleBNew", entryB.getTitle());
		Assert.assertEquals("testTitleCNew", entryC.getTitle());
	}

	@Test
	public void testGetSetText() {
		Assert.assertEquals("testTextA", entryA.getText());
		Assert.assertEquals("testTextB", entryB.getText());
		Assert.assertEquals("testTextC", entryC.getText());

		entryA.setText("testTextANew");
		entryB.setText("testTextBNew");
		entryC.setText("testTextCNew");

		Assert.assertEquals("testTextANew", entryA.getText());
		Assert.assertEquals("testTextBNew", entryB.getText());
		Assert.assertEquals("testTextCNew", entryC.getText());

		entryA.setText("testTextA");
		entryB.setText("testTextB");
		entryC.setText("testTextC");
	}

	@Test
	public void testGetSetDone() {
		Assert.assertFalse(entryA.getDone());
		Assert.assertFalse(entryB.getDone());
		Assert.assertFalse(entryC.getDone());

		entryA.setDone(true);
		entryB.setDone(true);
		entryC.setDone(true);

		Assert.assertTrue(entryA.getDone());
		Assert.assertTrue(entryB.getDone());
		Assert.assertTrue(entryC.getDone());

		entryA.setDone(false);
		entryB.setDone(false);
		entryC.setDone(false);
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(entryA.equals(entryA));

		Assert.assertFalse(entryA.equals(new Object()));
		Assert.assertFalse(entryA.equals(entryB));
		Assert.assertFalse(entryA.equals(entryC));

		TodoEntry entry = new TodoEntry(entryA.getTitle(), entryA.getText());

		Assert.assertFalse(entryA.equals(entry));
	}

	@Test
	public void testToString() {
		Assert.assertEquals("TodoEntry{" + "id='" + entryA.getId() + '\'' + ", title='" + "testTitleA" + '\'' + ", text='" + "testTextA" + '\'' + ", isDone=" + false + '}', entryA.toString());
	}
}
