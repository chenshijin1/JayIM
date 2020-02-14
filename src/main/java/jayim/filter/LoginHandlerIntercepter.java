//package jayim.filter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class LoginHandlerIntercepter implements HandlerInterceptor
//{
//
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//        HttpServletResponse response, Object arg2, Exception arg3)
//        throws Exception
//    {
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
//        Object arg2, ModelAndView arg3)
//        throws Exception
//    {
//
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1,
//        Object arg2)
//        throws Exception
//    {
//        String requestURI = request.getRequestURI();
//        if (requestURI.contains("index") ||requestURI.contains("jsp")||requestURI.contains("html"))
//        {
//            System.out.println(requestURI);
//            return true;
//        }
//        else
//        {
//            //说明处在编辑的页面
//            HttpSession session = request.getSession();
//
//            Object user=session.getAttribute("user");
//            if (user == null)
//            {
//                //没有登陆，转向登陆界面
//                request.setAttribute("msg", "您还没有登录，请先登录！");
//                request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, arg1);
//                return false;
//
//            }
//            else
//            {
//                //登陆成功的用户
//                return true;
//            }
//        }
//    }
//
//}