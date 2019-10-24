package by.trjava.pashkovich.facultative.service.validation;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class FieldValidatorTest {
    @Test
    public void testCheckEmailInvalid() {
        String email = "invalid email";
        assertFalse(FieldValidator.checkEmail(email));
    }

    @Test
    public void testCheckEmailCorrect() {
        String email = "email@gmail.by";
        assertTrue(FieldValidator.checkEmail(email));
    }

    @Test
    public void testIsEmptyObjectNull() {
        Object object = null;
        assertTrue(FieldValidator.isEmpty(object));
    }

    @Test
    public void testIsEmptyStringEmpty() {
        String string = "";
        assertTrue(FieldValidator.isEmpty(string));
    }

    @Test
    public void testIsEmptyCollectionEmpty() {
        Collection collection = new HashSet();
        assertTrue(FieldValidator.isEmpty(collection));
    }

    @Test
    public void testIsEmptyString() {
        String string = "string";
        assertFalse(FieldValidator.isEmpty(string));
    }

    @Test
    public void testIsWholeNumberEmpty() {
        String string = "";
        assertFalse(FieldValidator.isWholeNumber(string));
    }

    @Test
    public void testIsWholeNumberNegative() {
        String string = "-5";
        assertTrue(FieldValidator.isWholeNumber(string));
    }

    @Test
    public void testIsWholeNumberPositive() {
        String string = "35";
        assertTrue(FieldValidator.isWholeNumber(string));
    }

    @Test
    public void testIsWholeNumberDouble() {
        String string = "56.3";
        assertFalse(FieldValidator.isWholeNumber(string));
    }

    @Test
    public void testCheckTextFieldFormatCorrect() {
        String string = "string";
        assertTrue(FieldValidator.checkTextFieldFormat(string));
    }

    @Test
    public void testCheckTextFieldFormatIncorrect() {
        String string = "string2";
        assertFalse(FieldValidator.checkTextFieldFormat(string));
    }
    @Test
    public void testCheckDateCorrect(){
        String string = "2019-03-10";
        assertTrue(FieldValidator.checkDate(string));
    }
    @Test
    public void testCheckDateInvalidMonth(){
        String string = "2019-13-10";
        assertFalse(FieldValidator.checkDate(string));
    }
    @Test
    public void testCheckDateInvalidDay(){
        String string = "2019-03-40";
        assertFalse(FieldValidator.checkDate(string));
    }
    @Test
    public void testCheckPhoneCorrect(){
        String string = "+375(33)222-22-22";
        assertTrue(FieldValidator.checkPhone(string));
    }
    @Test
    public void testCheckPhoneInvlid(){
        String string = "+375(33)2222222";
        assertFalse(FieldValidator.checkPhone(string));
    }
    @Test
    public void testCheckTimeCorrect(){
        String string = "23:20";
        assertTrue(FieldValidator.checkTime(string));
    }
    @Test
    public void testCheckTimeInvalidHour(){
        String string = "25:20";
        assertFalse(FieldValidator.checkTime(string));
    }
    @Test
    public void testCheckTimeInvalidMinute(){
        String string = "12:70";
        assertFalse(FieldValidator.checkTime(string));
    }
}