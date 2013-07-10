/*
  	Copyright (c) 2013 GoPivotal, Inc. All Rights Reserved.

	This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; only version 2 of the License, and no
    later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

	The full text of the GPL is provided in the COPYING file.
*/
package vmware.au.se.sqlfireweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.utils.ConnectionManager;

@Controller
public class LogoutController 
{
	protected static Logger logger = Logger.getLogger("controller");

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout
      (Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception 
    {
    	logger.debug("Received request to logout of SQLFire*Web");

    	// remove connection from list
    	ConnectionManager cm = ConnectionManager.getInstance();
    	cm.removeConnection(session.getId());
    	
    	session.invalidate();
    	
    	response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");
    	
    	return null;
    }
}
