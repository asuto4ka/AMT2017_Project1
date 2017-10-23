/*
 -----------------------------------------------------------------------------------
 File        : AccesFilter.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : 
 Note(s) : 
 -----------------------------------------------------------------------------------
 */
package com.mycompany.mvc.web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nums
 */
public class AccesFilter implements Filter {

   private static final boolean debug = false;

   // The filter configuration object we are associated with.  If
   // this value is null, this filter instance is not currently
   // configured. 
   private FilterConfig filterConfig = null;

   public AccesFilter() {
   }

   private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response)
           throws IOException, ServletException {
      if (debug) {
         log("AccesFilter:DoBeforeProcessing");
      }
   }

   private void doAfterProcessing(RequestWrapper request, ResponseWrapper response)
           throws IOException, ServletException {
      if (debug) {
         log("AccesFilter:DoAfterProcessing");
      }
   }

   /**
    *
    * @param request The servlet request we are processing
    * @param response The servlet response we are creating
    * @param chain The filter chain we are processing
    *
    * @exception IOException if an input/output error occurs
    * @exception ServletException if a servlet error occurs
    */
   @Override
   public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain)
           throws IOException, ServletException {

      if (debug) {
         log("AccesFilter:doFilter()");
      }
      //wrapper creation
      RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
      ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
      // get the session 
      HttpSession session = wrappedRequest.getSession();
      // if the session is not actif or id does not existe --> login
      if (session == null || session.getAttribute("id") == null) {
         wrappedResponse.sendRedirect(wrappedRequest.getContextPath() + "/Login");
      }

      chain.doFilter(wrappedRequest, wrappedResponse);
   }

   /**
    * Return the filter configuration object for this filter.
    */
   public FilterConfig getFilterConfig() {
      return (this.filterConfig);
   }

   /**
    * Set the filter configuration object for this filter.
    *
    * @param filterConfig The filter configuration object
    */
   public void setFilterConfig(FilterConfig filterConfig) {
      this.filterConfig = filterConfig;
   }

   /**
    * Destroy method for this filter
    */
   @Override
   public void destroy() {
   }

   /**
    * Init method for this filter
    */
   @Override
   public void init(FilterConfig filterConfig) {
      this.filterConfig = filterConfig;
      if (filterConfig != null) {
         if (debug) {
            log("AccesFilter: Initializing filter");
         }
      }
   }

   /**
    * Return a String representation of this object.
    */
   @Override
   public String toString() {
      if (filterConfig == null) {
         return ("AccesFilter()");
      }
      StringBuffer sb = new StringBuffer("AccesFilter(");
      sb.append(filterConfig);
      sb.append(")");
      return (sb.toString());

   }

   private void sendProcessingError(Throwable t, ServletResponse response) {
      String stackTrace = getStackTrace(t);

      if (stackTrace != null && !stackTrace.equals("")) {
         try {
            response.setContentType("text/html");
            PrintStream ps = new PrintStream(response.getOutputStream());
            PrintWriter pw = new PrintWriter(ps);
            pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

            // PENDING! Localize this for next official release
            pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
            pw.print(stackTrace);
            pw.print("</pre></body>\n</html>"); //NOI18N
            pw.close();
            ps.close();
            response.getOutputStream().close();
         } catch (Exception ex) {
         }
      } else {
         try {
            PrintStream ps = new PrintStream(response.getOutputStream());
            t.printStackTrace(ps);
            ps.close();
            response.getOutputStream().close();
         } catch (Exception ex) {
         }
      }
   }

   public static String getStackTrace(Throwable t) {
      String stackTrace = null;
      try {
         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         t.printStackTrace(pw);
         pw.close();
         sw.close();
         stackTrace = sw.getBuffer().toString();
      } catch (Exception ex) {
      }
      return stackTrace;
   }

   public void log(String msg) {
      filterConfig.getServletContext().log(msg);
   }

   /**
    * This request wrapper class extends the support class HttpServletRequestWrapper,
    * which implements all the methods in the HttpServletRequest interface, as
    * delegations to the wrapped request. You only need to override the methods that
    * you need to change. You can get access to the wrapped request using the method
    * getRequest()
    */
   class RequestWrapper extends HttpServletRequestWrapper {

      public RequestWrapper(HttpServletRequest request) {
         super(request);
      }

      // You might, for example, wish to add a setParameter() method. To do this
      // you must also override the getParameter, getParameterValues, getParameterMap,
      // and getParameterNames methods.
      protected Hashtable localParams = null;

      public void setParameter(String name, String[] values) {
         if (debug) {
            System.out.println("AccesFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
         }

         if (localParams == null) {
            localParams = new Hashtable();
            // Copy the parameters from the underlying request.
            Map wrappedParams = getRequest().getParameterMap();
            Set keySet = wrappedParams.keySet();
            for (Iterator it = keySet.iterator(); it.hasNext();) {
               Object key = it.next();
               Object value = wrappedParams.get(key);
               localParams.put(key, value);
            }
         }
         localParams.put(name, values);
      }

      @Override
      public String getParameter(String name) {
         if (debug) {
            System.out.println("AccesFilter::getParameter(" + name + ") localParams = " + localParams);
         }
         if (localParams == null) {
            return getRequest().getParameter(name);
         }
         Object val = localParams.get(name);
         if (val instanceof String) {
            return (String) val;
         }
         if (val instanceof String[]) {
            String[] values = (String[]) val;
            return values[0];
         }
         return (val == null ? null : val.toString());
      }

      @Override
      public String[] getParameterValues(String name) {
         if (debug) {
            System.out.println("AccesFilter::getParameterValues(" + name + ") localParams = " + localParams);
         }
         if (localParams == null) {
            return getRequest().getParameterValues(name);
         }
         return (String[]) localParams.get(name);
      }

      @Override
      public Enumeration getParameterNames() {
         if (debug) {
            System.out.println("AccesFilter::getParameterNames() localParams = " + localParams);
         }
         if (localParams == null) {
            return getRequest().getParameterNames();
         }
         return localParams.keys();
      }

      @Override
      public Map getParameterMap() {
         if (debug) {
            System.out.println("AccesFilter::getParameterMap() localParams = " + localParams);
         }
         if (localParams == null) {
            return getRequest().getParameterMap();
         }
         return localParams;
      }
   }

   /**
    * This response wrapper class extends the support class
    * HttpServletResponseWrapper, which implements all the methods in the
    * HttpServletResponse interface, as delegations to the wrapped response. You only
    * need to override the methods that you need to change. You can get access to the
    * wrapped response using the method getResponse()
    */
   class ResponseWrapper extends HttpServletResponseWrapper {

      public ResponseWrapper(HttpServletResponse response) {
         super(response);
      }
   }
}
