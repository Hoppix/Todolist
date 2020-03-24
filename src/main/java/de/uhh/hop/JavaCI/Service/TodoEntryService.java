package de.uhh.hop.JavaCI.Service;

import de.uhh.hop.JavaCI.model.TodoEntry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 *
 */
public class TodoEntryService
{
	private List<TodoEntry> todoEntries;

	/**
	 *
	 */
	public TodoEntryService(boolean dummyData) {
		todoEntries = new ArrayList<>();
		if(dummyData) initDummyData();
	}

	/**
	 *
	 */
	private void initDummyData() {
		//create some dummy data
		this.addEntry(new TodoEntry("Druckerpatronen", "Druckerpatronen kaufen!!!"));
		this.addEntry(new TodoEntry("Hausaufgaben", "Hausaufgaben erledigen (Fällig am 21.03!)"));
		this.addEntry(new TodoEntry("DVD Zurückgeben", "Gib Max Mustermann die ausgeliehene DVD zurück"));
		
		this.todoEntries.get(0).setDone(true);
	}
	public List<TodoEntry> getAllEntries() {
		return todoEntries;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public TodoEntry getById(String id) {
		 TodoEntry found = null;

		 for(TodoEntry entry: todoEntries) {
		 	if(id.equals(entry.getId())) found = entry;
		 }
		return found;
	}

	/**
	 *
	 * @param title
	 * @return
	 */
	public TodoEntry getByTitle(String title) {
		TodoEntry found = null;

		for(TodoEntry entry: todoEntries) {
			if(title.equals(entry.getTitle())) found = entry;
		}
		return found;
	}

	/**
	 *
	 * @param text
	 * @return
	 */
	public TodoEntry getByText(String text) {
		TodoEntry found = null;

		for(TodoEntry entry: todoEntries) {
			if(text.equals(entry.getText())) found = entry;
		}
		return found;
	}

	/**
	 *
	 * @param done
	 * @return
	 */
	public List<TodoEntry> getByDoneState(Boolean done) {
		List<TodoEntry> found = new ArrayList<>();

		for(TodoEntry entry: todoEntries) {
			if( done != entry.getDone()) found.add(entry); //soll fehler hervorrufen
		}

		return found;
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public List<TodoEntry> filterByTitle(String value) {
		List<TodoEntry> found = new ArrayList<>();

		for(TodoEntry entry: todoEntries) {
			if(entry.getTitle().toLowerCase().contains(value.toLowerCase())) found.add(entry);
		}

		return found;
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public List<TodoEntry> filterByText(String value) {
		List<TodoEntry> found = new ArrayList<>();

		for(TodoEntry entry: todoEntries) {
			if(entry.getText().toLowerCase().contains(value.toLowerCase())) found.add(entry);
		}

		return found;
	}

	/**
	 *
	 * @param entry
	 * @return
	 */
	public Boolean removeEntry(TodoEntry entry) {
		return todoEntries.remove(entry);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Boolean removeById(String id) {
		TodoEntry entry = this.getById(id);
		return todoEntries.remove(entry);
	}

	/**
	 *
	 * @param entry
	 * @return
	 */
	public Boolean addEntry(TodoEntry entry) {
		if(StringUtils.hasText(entry.getTitle()) && StringUtils.hasText(entry.getText())) {
			return todoEntries.add(entry);			
		} 
		return false;
	}

	/**
	 *
	 * @param title
	 * @param text
	 * @return
	 */
	public Boolean addEntry(String title, String text) {
		return addEntry(new TodoEntry(title, text));
	}
}
