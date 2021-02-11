package org.scholanova.mealdeliverapi.application;

import org.scholanova.mealdeliverapi.domain.Menu.MenuMauvaisTypeException;
import org.scholanova.mealdeliverapi.domain.Restaurant.RestaurantNonTrouveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.ControllerAdvice(assignableTypes =  {RestaurantController.class, NourritureController.class, MenuController.class})
public class ControllersAdvice {

        @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
        @ExceptionHandler(MenuMauvaisTypeException.class)
        @ResponseBody
        ErrorInfo
        handleMenuMauvaisTypeException(HttpServletRequest req, Exception ex) {
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