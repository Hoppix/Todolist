package de.uhh.hop.JavaCI.web;

import de.uhh.hop.JavaCI.Service.TodoEntryService;
import de.uhh.hop.JavaCI.model.TodoEntry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BaseWebController {

	TodoEntryService service;
	public BaseWebController() {
		service = new TodoEntryService(true);
	}


	/**
	 *
	 * @return
	 */
	@GetMapping("/todoentries")
	public List<TodoEntry> getEntries() {
		return service.getAllEntries();
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/todoentries({id})")
	public TodoEntry getOne(@PathVariable String id) {
		return service.getById(id);
	}

	/**
	 *
	 * @param title
	 * @param text
	 * @return
	 */
	@PostMapping("/todoentries")
	public Boolean addEntry(@RequestParam(name="title", required=true) String title, @RequestParam(name="text", required=true) String text) {
		return service.addEntry(title, text);
	}

	@DeleteMapping("/todoentries({id})")
	public Boolean removeEntry(@PathVariable String id) {
		return service.removeById(id);
	}

	/**
	 *
	 * @param id
	 * @param done
	 * @return
	 */
	@PutMapping("/todoentries({id})")
	public TodoEntry setDone(@PathVariable String id, @RequestParam(name="done", required=true) boolean done) {
		TodoEntry entry = service.getById(id);
		entry.setDone(done);
		return entry;
	}

	/**
	 *
	 * @param done
	 * @return
	 */
	@GetMapping("/todoentries(done={done})")
	public List<TodoEntry> getDone(@PathVariable boolean done) {
		return service.getByDoneState(done);
	}

	/**
	 *
	 * @param title
	 * @return
	 */
	@GetMapping("/todoentries/filter(title={title})")
	public List<TodoEntry> filterByTitle(@PathVariable String title) {
		return service.filterByTitle(title);
	}

	/**
	 *
	 * @param text
	 * @return
	 */
	@GetMapping("/todoentries/filter(text={text})")
	public List<TodoEntry> filterByText(@PathVariable String text) {
		return service.filterByText(text);
	}


}
