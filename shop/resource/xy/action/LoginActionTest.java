package xy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.web.action.ActionFather;
import com.shopping.web.form.FormFather;

public class LoginActionTest extends ActionFather{

	@Override
	public String doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
		
		return "error";
	}

}
