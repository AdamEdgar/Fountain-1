/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.tools;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

// DOM imports
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomSerialiser {

  /** Indentation to use (default is no indentation) */
  private String indent = "";

  /** Line separator to use (default is for UNIX) */
  private String lineSeparator = "\n";

  /** Encoding for output (default is UTF-8) */
  private String encoding = "UTF-8";

  public void setLineSeparator(String lineSeparator) {
    this.lineSeparator = lineSeparator;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public void setIndent(int numSpaces) {
    StringBuffer buffer = new StringBuffer();
    for (int i=0; i<numSpaces; i++)
      buffer.append(" ");
    this.indent = buffer.toString();
  }

  public void serialize(Document doc, OutputStream out)
    throws IOException {
        
    Writer writer = new OutputStreamWriter(out, encoding);
    serialize(doc, writer);
  }

  public void serialize(Document doc, File file)
    throws IOException {

    Writer writer = new FileWriter(file);
    serialize(doc, writer);
  }

  public void serialize(Document doc, Writer writer)
  throws IOException {
	  serializeNode(doc, writer, "  ");
	  writer.flush( );
  }

  private void serializeNode(Node node, Writer writer, String indentLevel) throws IOException {

		// Determine action based on node type
		switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE:
				Document doc = (Document)node;
				writer.write("<?xml version=\"");
				writer.write(doc.getXmlVersion());
				writer.write("\" encoding=\"UTF-8\" standalone=\"");  
				if (doc.getXmlStandalone()){
				    writer.write("yes");
				}
				else{
				    writer.write("no");
				}
				writer.write("\"");
				writer.write("?>");
				writer.write(lineSeparator);
				serializeNode(doc.getDocumentElement( ), writer, "");
				break;
			case Node.ELEMENT_NODE:
				String name = node.getNodeName();
				writer.write(indentLevel + "<" + name);
				NamedNodeMap attributes = node.getAttributes();
				for (int i=0; i<attributes.getLength(); i++) {
				    Node current = attributes.item(i);
				    writer.write(" " + current.getNodeName() + "=\"");
				    print(writer, current.getNodeValue());
				    writer.write("\"");
				}
				writer.write(">");
				                
				// recurse on each child
				NodeList children = node.getChildNodes();
				if (children != null) {
				    if ((children.item(0) != null) && (children.item(0).getNodeType() == Node.ELEMENT_NODE)){                         
				   //   writer.write(lineSeparator);
				    }
				    for (int i=0; i<children.getLength(); i++){ 
				      serializeNode(children.item(i), writer, indentLevel + indent);
				      if ((children.item(0) != null) && (children.item(children.getLength( )-1).getNodeType( ) == Node.ELEMENT_NODE)){
				    	  writer.write(indentLevel);
				      }
				    }
				}
				                
				writer.write("</" + name + ">");
			//	writer.write(lineSeparator);
				break;

			case Node.TEXT_NODE:
				print(writer, node.getNodeValue());
				break;

			case Node.CDATA_SECTION_NODE:
				writer.write("<![CDATA[");
				writer.write (node.getNodeValue());
				writer.write("]]>");
				break;
				  
			case Node.COMMENT_NODE:
				writer.write(indentLevel + "<!-- " + node.getNodeValue( ) + " -->");
				writer.write(lineSeparator);
				break;
			case Node.PROCESSING_INSTRUCTION_NODE:
				writer.write("<?" + node.getNodeName( ) +" " + node.getNodeValue( ) +"?>");                
				writer.write(lineSeparator);
				break;
			case Node.ENTITY_REFERENCE_NODE:
				writer.write("&" + node.getNodeName( ) + ";");
				break;
			case Node.DOCUMENT_TYPE_NODE: 
				DocumentType docType = (DocumentType)node;
				String publicId = docType.getPublicId();
				String systemId = docType.getSystemId();
				String internalSubset = docType.getInternalSubset();
				writer.write("<!DOCTYPE " + docType.getName( ));
				if (publicId != null){
				    writer.write(" PUBLIC \"" +publicId + "\" ");
				}
				else{
				    writer.write(" SYSTEM ");
				}
				writer.write("\"" + systemId + "\"");
				if (internalSubset != null){
				    writer.write(" [" + internalSubset + "]");
				}
				writer.write(">");
				writer.write(lineSeparator);
				break;
		}
  }

  private void print(Writer writer, String s) throws IOException{

	  if (s == null) 
	  {
		  return;
	  }
	  for (int i=0, len=s.length(); i<len; i++) {
		  char c = s.charAt(i);
		  switch(c) {
		  	case '<':
		  		writer.write("&lt;");
		  		break;
		  	case '>':
		  		writer.write("&gt;");
		  		break;
		  	case '&':
		  		writer.write("&amp;");
		  		break;
		  	case '\r':
		  		writer.write("&#xD;");
		  		break;
		  	default:
		  		writer.write(c);
		  }
		  
	  }

  }
}
