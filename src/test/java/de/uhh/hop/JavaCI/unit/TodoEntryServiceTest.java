package de.uhh.hop.JavaCI.unit;

import de.uhh.hop.JavaCI.Service.TodoEntryService;
import de.uhh.hop.JavaCI.model.TodoEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TodoEntryServiceTest
{
	TodoEntryService testService;

	@Before
	public void initTest()
	{
		testService = new TodoEntryService(false);
		testService.addEntry(new TodoEntry("serviceTestEntryTitleA", "serviceTestEntryTextA"));
		testService.addEntry(new TodoEntry("serviceTestEntryTitleB", "serviceTestEntryTextB"));
		testService.addEntry(new TodoEntry("serviceTestEntryTitleC", "serviceTestEntryTextC"));
		testService.addEntry(new TodoEntry("serviceTestEntryTitleD", "serviceTestEntryTextD"));
		testService.addEntry(new TodoEntry("serviceTestEntryTitleE", "serviceTestEntryTextE"));
	}

	@Test
	public void testGetAllEntries()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		Assert.assertNotNull(entries);
		Assert.assertEquals(5, entries.size()); //since 3 dummy entries are created

	}

	@Test
	public void testGetById()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		TodoEntry entry = entries.get(0);

		TodoEntry result = testService.getById(entry.getId());

		Assert.assertNotNull(result);
		Assert.assertEquals(result, entry);
	}

	@Test
	public void testGetByTitle()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		TodoEntry entry = entries.get(3);

		TodoEntry result = testService.getByTitle(entry.getTitle());

		Assert.assertNotNull(result);
		Assert.assertEquals(result, entry);
	}

	@Test
	public void testGetByText()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		TodoEntry entry = entries.get(3);

		TodoEntry result = testService.getByText(entry.getText());

		Assert.assertNotNull(result);
		Assert.assertEquals(result, entry);
	}

	@Test
	public void testGetByDoneState()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		TodoEntry entryA = entries.get(0);
		TodoEntry entryB = entries.get(1);

		entryA.setDone(true);
		entryB.setDone(true);

		List<TodoEntry> doneEntries = testService.getByDoneState(true);
		List<TodoEntry> notDoneEntries = testService.getByDoneState(false);

		Assert.assertNotNull(doneEntries);
		Assert.assertNotNull(notDoneEntries);

		Assert.assertEquals(2, doneEntries.size());
		Assert.assertEquals(3, notDoneEntries.size());
		Assert.assertEquals(5, doneEntries.size() + notDoneEntries.size());

		Assert.assertTrue(doneEntries.contains(entryA) && doneEntries.contains(entryB));
	}

	@Test
	public void testFilterByTitle()
	{
		List<TodoEntry> entries = testService.filterByTitle("serviceTestEntry");

		Assert.assertNotNull(entries);
		Assert.assertEquals(5, entries.size());

		entries =  testService.filterByTitle("A");

		Assert.assertNotNull(entries);
		Assert.assertEquals(1, entries.size());
	}

	@Test
	public void testFilterByText()
	{
		List<TodoEntry> entries = testService.filterByText("serviceTestEntry");

		Assert.assertNotNull(entries);
		Assert.assertEquals(5, entries.size());

		entries =  testService.filterByText("A");

		Assert.assertNotNull(entries);
		Assert.assertEquals(1, entries.size());
	}

	@Test
	public void testRemoveEntry()
	{
		List<TodoEntry> entries = testService.getAllEntries();
		String entryAId = entries.get(0).getId();
		String entryBId = entries.get(1).getId();

		testService.removeById(entryAId);
		testService.removeById(entryBId);

		entries = testService.getAllEntries();

		Assert.assertEquals(3, entries.size());

		Assert.assertNull(testService.getById(entryAId));
		Assert.assertNull(testService.getById(entryBId));
	}

	@Test
	public void testAddEntry()
	{
		List<TodoEntry> entries = testService.getAllEntries();

		Assert.assertEquals(5, entries.size());

		testService.addEntry("newEntryTitle", "newEntryText");

		entries = testService.getAllEntries();

		Assert.assertEquals(6, entries.size());

		TodoEntry newEntry = testService.getByTitle("newEntryTitle");

		Assert.assertTrue(entries.contains(newEntry));
	}


}
