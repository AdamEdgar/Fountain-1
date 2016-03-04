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
package uk.gov.ofwat.fountain.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import uk.gov.ofwat.fountain.domain.form.FormPageTest;
import uk.gov.ofwat.fountain.domain.form.NavigationPaneTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( {EquationParserImplTest.class,
	                  FormulaTest.class,
	                  CodeTokenTest.class,
	                  ModelTest.class,
	                  PotTest.class,
	                  UserEditTest.class,
	                  DataTableTest.class,
	                  LockTest.class,
	                  ModelPropertiesMapTest.class,
	                  ItemPropertiesTest.class,
	                  CompanyTest.class,
	                  FormPageTest.class,
	                  NavigationPaneTest.class})
public class DomainTestSuite{
	public class AllTests {
	}
}
