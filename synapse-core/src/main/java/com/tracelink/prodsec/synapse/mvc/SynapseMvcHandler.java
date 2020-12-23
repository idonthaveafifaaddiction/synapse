package com.tracelink.prodsec.synapse.mvc;

import com.tracelink.prodsec.synapse.sidebar.service.SidebarService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * The MvcHandler ensures that the sidebar is configured properly for Synapse
 * ModelAndView requests. This is separate from the SynapseModelAndView in order
 * to decrease calls to the sidebar service and ensure the sidebar service is
 * pulled in to the object correctly (i.e. autowired)
 *
 * @author csmith
 */
public class SynapseMvcHandler extends HandlerInterceptorAdapter {

	private final SidebarService sidebarService;

	public SynapseMvcHandler(SidebarService sidebarService) {
		this.sidebarService = sidebarService;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null && isSynapseContainerView(modelAndView) && !isRedirectView(
				modelAndView)) {
			modelAndView.addObject("sidebar", sidebarService.getSidebar());
		}

		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * We will only add the sidebar if this {@link ModelAndView} is a
	 * {@link SynapseModelAndView} and needs to use the "well-view" mechanism
	 *
	 * @param mv the {@link ModelAndView}
	 * @return true if this is a {@link SynapseModelAndView} that needs to use the
	 * "well-view" mechanism
	 */
	private static boolean isSynapseContainerView(ModelAndView mv) {
		return SynapseModelAndView.DEFAULT_VIEW_TEMPLATE.equals(mv.getViewName())
				&& mv.getModelMap().containsKey(SynapseModelAndView.CONTENT_VIEW_NAME);
	}

	/**
	 * On redirections, we don't need to call the sidebar yet, only on the
	 * redirected page's response, potentially
	 *
	 * @param mv the {@link ModelAndView}
	 * @return true if this seems to be a redirect call, false otherwise
	 */
	private static boolean isRedirectView(ModelAndView mv) {
		View view = mv.getView();
		return (view instanceof SmartView && ((SmartView) view).isRedirectView());
	}

}
