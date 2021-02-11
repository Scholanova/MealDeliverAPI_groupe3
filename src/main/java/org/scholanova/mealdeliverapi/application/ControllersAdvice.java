package org.scholanova.mealdeliverapi.application;
import org.scholanova.mealdeliverapi.application.controllers.MenuController;
import org.scholanova.mealdeliverapi.application.controllers.NourritureController;
import org.scholanova.mealdeliverapi.application.controllers.RestaurantController;
import org.scholanova.mealdeliverapi.domain.Menu.Exception.MenuChoixIndisponibleException;
import org.scholanova.mealdeliverapi.domain.Menu.Exception.MenuMauvaisTypeException;

import org.scholanova.mealdeliverapi.domain.Restaurant.Exception.RestaurantNonTrouveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes =  {RestaurantController.class, MenuController.class, NourritureController.class})
public class ControllersAdvice {

        @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
        @ExceptionHandler(MenuMauvaisTypeException.class)
        @ResponseBody
        ErrorInfo
        handleMenuMauvaisTypeException(HttpServletRequest req, Exception ex) {
            return new ErrorInfo(req.getRequestURL().toString(), ex);
        }

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(MenuChoixIndisponibleException.class)
        @ResponseBody
        ErrorInfo
        handleMenuChoixIndisponibleException(HttpServletRequest req, Exception ex) {
                return new ErrorInfo(req.getRequestURL().toString(), ex);
        }

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(RestaurantNonTrouveException.class)
        @ResponseBody
        ErrorInfo
        handleRestaurantNonTrouveException(HttpServletRequest req, Exception ex) {
                return new ErrorInfo(req.getRequestURL().toString(), ex);
        }
}
