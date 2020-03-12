package de.uhh.hop.JavaCI.integration;

import de.uhh.hop.JavaCI.model.TodoEntry;
import de.uhh.hop.JavaCI.integration.tools.RestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class BaseWebControllerIntegrationTest
{
	RestClient client;

	@Before
	public void initTest() {
		client = new RestClient("localhost:8080");
	}

	@Test
	public void testGetEntries() {
		List<HashMap> entries =  client.restGetList("/todoentries", new TodoEntry());
		Assert.assertNotNull(entries);
		Assert.assertEquals(3, entries.size());

		String entryId = (String) entries.get(0).get("id)");

		client.restGetTodoEntry("/todoentries", entryId);

	}

	@Test
	public void addRemoveEntry(){
		client.restPostTodoEntry("/todoentries", "restTestEntryTitleA", "restTestEntryTextB");
		List<HashMap> entries =  client.restGetList("/todoentries", new TodoEntry());
		Assert.assertEquals(4, entries.size());

		for (HashMap entry : entries)
		{
			if(entry.get("title").equals("restTestEntryTitleA"))
			{

				client.restDeleteTodoEntry("/todoentries", (String) entry.get("id"));
				return;
			}
		}
		Assert.fail("New entry has not been found!");

	}

	@Test
	public void testGetAllDone() {
		List<HashMap> entries =  client.restGetList("/todoentries(done=false)", new TodoEntry());
		Assert.assertEquals(2, entries.size());

		List<HashMap> doneEntries = client.restGetList("/todoentries(done=true)", new TodoEntry());
		Assert.assertEquals(1, doneEntries.size());

		//change the done status of a todoentry
		HashMap entry = entries.get(0);
		client.restPostTodoEntrySetDone("/todoentries(" + entry.get("id") + ")", true + "");

		doneEntries = client.restGetList("/todoentries(done=true)", new TodoEntry());
		Assert.assertEquals(1, doneEntries.size());

		client.restPostTodoEntrySetDone("/todoentries(" + entry.get("id") + ")", false + "");
	}

	@Test
	public void testFiltering() {
		//filter by title
		List<HashMap> entries =  client.restGetList("/todoentries/filter(title=" + "Drucker" + ")", new TodoEntry());
		Assert.assertEquals(1, entries.size());

		entries =  client.restGetList("/todoentries/filter(text=" + "haus" + ")", new TodoEntry());
		Assert.assertEquals(1, entries.size());

		//filter by text
		entries =  client.restGetList("/todoentries/filter(title=" + "Drucker" + ")", new TodoEntry());
		Assert.assertEquals(1, entries.size());

		entries =  client.restGetList("/todoentries/filter(text=" + "haus" + ")", new TodoEntry());
		Assert.assertEquals(1, entries.size());
	}
}
