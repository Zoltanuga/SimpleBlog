package by.it.util;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Paginator {
    public static final String PARAM_TOTAL_PAGES = "totalPages";
    public static final String PARAM_PAGE_ORDER = "pageOrder";
    public static final String PARAM_ITEMS_PER_PAGE = "itemsPerPage";
    public static final String PARAM_CURRENT_PAGE = "currentPage";
    public static final String PARAM_TAIL_INIT_GET_PARAMETERS = "&itemsPerPage=10&currentPage=1&sort=sort_by_date";
    public static final int VALUE_START_PAGE = 1;
    public static final int VALUE_DEFAULT_ITEMS_PER_PAGE = 10;


    public static int getTotalPages(ModelMap modelMap) {
        return modelMap.containsAttribute(PARAM_TOTAL_PAGES) ?
                Integer.parseInt(modelMap.get(PARAM_TOTAL_PAGES).toString()) : 0;
    }

    public static int getItemsPerPage(ModelMap modelMap) {
        return modelMap.containsAttribute(PARAM_ITEMS_PER_PAGE) ?
                Integer.parseInt(modelMap.get(PARAM_ITEMS_PER_PAGE).toString()) : 0;
    }

    public static int getCurrentPage(ModelMap modelMap) {
        return modelMap.containsAttribute(PARAM_CURRENT_PAGE) ?
                Integer.parseInt(modelMap.get(PARAM_CURRENT_PAGE).toString()) : 0;
    }

    public static int getCurrentPageInCaseOfChange(ModelMap model, int totalPages) {
        int currentPage;

        if (!model.containsAttribute(PARAM_CURRENT_PAGE) ) {
            currentPage = VALUE_START_PAGE;
        } else {
           /* if (request.getParameter(PARAM_CURRENT_PAGE).isEmpty()) {
                currentPage = VALUE_START_PAGE;
            } else {*/
           // Map<String, Object> parameters=model.asMap();

                String currentPageParam =  model.get(PARAM_CURRENT_PAGE).toString();

                currentPage = Integer.parseInt(currentPageParam);
                if (currentPage > totalPages) {
                    currentPage = totalPages;
                }

        }
        return currentPage;
    }

    public static int calculatePageQuantity(int itemsPerPage, long itemsQuantity) {
        return (itemsQuantity % itemsPerPage == 0) && (itemsQuantity / itemsPerPage != 0)
                ? ((int) itemsQuantity / itemsPerPage) : (int) itemsQuantity / itemsPerPage + 1;
    }

    public static void setTotalPages(ModelMap modelMap, int totalPages) {
        modelMap.put(PARAM_TOTAL_PAGES, totalPages);
    }

    public static void setItemsPerPage(ModelMap modelMap, int itemsPerPage) {
        modelMap.put(PARAM_ITEMS_PER_PAGE, itemsPerPage);
    }

    public static void setCurrentPage(ModelMap modelMap, int currentPage) {
        modelMap.put(PARAM_CURRENT_PAGE, currentPage);
    }

    public static void setPageOrder(ModelMap modelMap, int totalPages) {
        List<Integer> pageOrder = new ArrayList<Integer>();
        for (int i = 0; i < totalPages; i++) {
            pageOrder.add(i + 1);
        }
        modelMap.put(PARAM_PAGE_ORDER, pageOrder);
    }

}
