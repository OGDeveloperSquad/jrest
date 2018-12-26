package com.og.jrest.routing;

/**
 * Represents a section of the '/' delimited template string.
 * 
 * @author Matthew.Shoemaker
 *
 */
public interface IPathSegment {

	/**
	 * Reports whether this segment was specified in the template as optional. This
	 * only applies to parameter segments, as actions or controllers cannot be
	 * optional, and static segments are never optional.
	 * 
	 * @return Whether the parameter is optional
	 */
	boolean isOptional();

	/**
	 * Sets whether or not the segment is optional.
	 * 
	 * @param isOptional whether or not the segment is optional
	 */
	void setOptional(boolean isOptional);

	/**
	 * Returns the raw text of the segment as specified in the template.
	 * 
	 * @return the raw text of this segment as is appears in the template
	 */
	String getText();

	/**
	 * Sets the text of this segment to text.
	 * 
	 * @param text the text of this segment
	 */
	void setText(String text);

}
