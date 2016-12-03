package com.cn.modeandview;

import java.util.Map;

import javax.swing.text.View;

import org.springframework.ui.ModelMap;

public class ModelAndView {

/** View instance or view name String */

private Object view;//属性view

/** Model Map */

private ModelMap model;//属性model

/**

 * Indicates whether or not this instance has been cleared with a call to {@link #clear()}.

 */

private boolean cleared = false;



public ModelAndView() {

}


   //常使用的构造器

public ModelAndView(String viewName) {

this.view = viewName;

}



public ModelAndView(View view) {

this.view = view;

}



public ModelAndView(String viewName, Map<String, ?> model) {

this.view = viewName;

if (model != null) {

getModelMap().addAllAttributes(model);

}

}



public ModelAndView(View view, Map<String, ?> model) {

this.view = view;

if (model != null) {

getModelMap().addAllAttributes(model);

}

}

/**

 * Convenient constructor to take a single model object.

 * @param viewName name of the View to render, to be resolved

 * by the DispatcherServlet's ViewResolver

 * @param modelName name of the single entry in the model

 * @param modelObject the single model object

 */

public ModelAndView(String viewName, String modelName, Object modelObject) {

this.view = viewName;

addObject(modelName, modelObject);

}

 

/**

 * Convenient constructor to take a single model object.

 * @param view View object to render

 * @param modelName name of the single entry in the model

 * @param modelObject the single model object

 */

public ModelAndView(View view, String modelName, Object modelObject) {

this.view = view;

addObject(modelName, modelObject);

}

/**

 * Set a view name for this ModelAndView, to be resolved by the

 * DispatcherServlet via a ViewResolver. Will override any

 * pre-existing view name or View.

 */

public void setViewName(String viewName) {

this.view = viewName;

}

/**

 * Return the view name to be resolved by the DispatcherServlet

 * via a ViewResolver, or <code>null</code> if we are using a View object.

 */

public String getViewName() {

return (this.view instanceof String ? (String) this.view : null);

}

/**

 * Set a View object for this ModelAndView. Will override any

 * pre-existing view name or View.

 */

public void setView(View view) {

this.view = view;

}

/**

 * Return the View object, or <code>null</code> if we are using a view name

 * to be resolved by the DispatcherServlet via a ViewResolver.

 */

public View getView() {

return (this.view instanceof View ? (View) this.view : null);

}

/**

 * Indicate whether or not this <code>ModelAndView</code> has a view, either

 * as a view name or as a direct {@link View} instance.

 */

public boolean hasView() {

return (this.view != null);

}

/**

 * Return whether we use a view reference, i.e. <code>true</code>

 * if the view has been specified via a name to be resolved by the

 * DispatcherServlet via a ViewResolver.

 */

public boolean isReference() {

return (this.view instanceof String);

}

/**

 * Return the model map. May return <code>null</code>.

 * Called by DispatcherServlet for evaluation of the model.

 */

protected Map<String, Object> getModelInternal() {

return this.model;

}

/**

 * Return the underlying <code>ModelMap</code> instance (never <code>null</code>).

 */

public ModelMap getModelMap() {

if (this.model == null) {

this.model = new ModelMap();

}

return this.model;

}

/**

 * Return the model map. Never returns <code>null</code>.

 * To be called by application code for modifying the model.

 */

public Map<String, Object> getModel() {

return getModelMap();

}

/**

 * Add an attribute to the model.

 * @param attributeName name of the object to add to the model

 * @param attributeValue object to add to the model (never <code>null</code>)

 * @see ModelMap#addAttribute(String, Object)

 * @see #getModelMap()

 */

public ModelAndView addObject(String attributeName, Object attributeValue) {

getModelMap().addAttribute(attributeName, attributeValue);

return this;

}

/**

 * Add an attribute to the model using parameter name generation.

 * @param attributeValue the object to add to the model (never <code>null</code>)

 * @see ModelMap#addAttribute(Object)

 * @see #getModelMap()

 */

public ModelAndView addObject(Object attributeValue) {

getModelMap().addAttribute(attributeValue);

return this;

}

/**

 * Add all attributes contained in the provided Map to the model.

 * @param modelMap a Map of attributeName -> attributeValue pairs

 * @see ModelMap#addAllAttributes(Map)

 * @see #getModelMap()

 */

public ModelAndView addAllObjects(Map<String, ?> modelMap) {

getModelMap().addAllAttributes(modelMap);

return this;

}

/**

 * Clear the state of this ModelAndView object.

 * The object will be empty afterwards.

 * <p>Can be used to suppress rendering of a given ModelAndView object

 * in the <code>postHandle</code> method of a HandlerInterceptor.

 * @see #isEmpty()

 * @see HandlerInterceptor#postHandle

 */

public void clear() {

this.view = null;

this.model = null;

this.cleared = true;

}




/**

 * Return diagnostic information about this model and view.

 */

@Override

public String toString() {

StringBuilder sb = new StringBuilder("ModelAndView: ");

if (isReference()) {

sb.append("reference to view with name '").append(this.view).append("'");

}

else {

sb.append("materialized View is [").append(this.view).append(']');

}

sb.append("; model is ").append(this.model);

return sb.toString();

}

}
