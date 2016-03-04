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
package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;


public interface CompanyDao extends CachableCodedDao, CachableEntityDao {

	public int create(Company company);
	
	public Company findById(int id);

	/**
	 * Only gets current companies, ignores those that are no longer used.
	 * @return List of companies
	 */
	public List<Company> getAllCurrent();
	
	/**
	 * Gets ALL companies including historic companies. 
	 * Ordered by WASC, WOC then historic
	 * @return List of companies
	 */
	public List<Company> getAll();
	
	public Company findByCode(String company);
	
	public List<CompanyType> getAllCompanyTypes();

	/**
	 * @param companyTypeName (e.g. WASC)
	 * @return
	 */
	public int findCompanyTypeId(String companyTypeName); 
	
	public CompanyType findByCompanyTypeId(int id);
	
	public CompanyType findByCompanyTypeCode(String code);

	List<Company> getCompaniesForRunCompanyTemplate(int runCompanyTemplateId);

}
