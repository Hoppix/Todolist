package de.uhh.hop.JavaCI.model;

import java.util.Objects;

public class TodoEntry extends BaseDataObject
{

	private String title;

	private String text;

	private Boolean isDone;


	public TodoEntry() {}

	public TodoEntry(String title, String text) {
		super();
		this.title = title;
		this.text = text;
		this.isDone = false;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}


	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}


	public Boolean getDone()
	{
		return isDone;
	}

	public void setDone(Boolean done)
	{
		isDone = done;
	}

	@Override
	public String toString()
	{
		return "TodoEntry{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", text='" + text + '\'' + ", isDone=" + isDone + '}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		TodoEntry todoEntry = (TodoEntry) o;
		return Objects.equals(id, todoEntry.id) && Objects.equals(title, todoEntry.title) && Objects.equals(text, todoEntry.text) && Objects.equals(isDone, todoEntry.isDone);
	}

	@Override
	public int hashCode()
	{

		return Objects.hash(id, title, text, isDone);
	}
}
