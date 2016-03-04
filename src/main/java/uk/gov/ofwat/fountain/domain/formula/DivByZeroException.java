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

package uk.gov.ofwat.fountain.domain.formula;

public class DivByZeroException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64700702857990222L;

	public DivByZeroException() {
		super();
	}

	public DivByZeroException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DivByZeroException(String arg0) {
		super(arg0);
	}

	public DivByZeroException(Throwable arg0) {
		super(arg0);
	}

}
