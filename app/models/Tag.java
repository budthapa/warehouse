package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Tag extends Model {

	private static final long serialVersionUID = 5476207418180200244L;
	@Id
	public Long primaryKey;

	@Required
	public String tagId;

	public String name;
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Product> product = new ArrayList<>();

	public Tag(String tagId) {
		this.tagId = tagId;
	}

	/*
	 * Tag name should not be "Tag", return error if user passes "Tag" as tag name
	 */
	public String validate() {
		return ("Tag".equals(this.tagId) ? "Invalid tag name" : null);
	}

	public static Finder<Long, Tag> find = new Finder<>(Long.class, Tag.class);
	
	public String toString(){
		return String.format("TagId : %s", tagId);
	}
}
