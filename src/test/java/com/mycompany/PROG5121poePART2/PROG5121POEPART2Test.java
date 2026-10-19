package com.mycompany.PROG5121poePART2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PROG5121POEPART2Test {

    // ---------- checkUserName ----------

    @Test
    public void usernameWithUnderscoreAndFiveCharsIsValid() {
        assertTrue(PROG5121POEPART2.checkUserName("user_"));
    }

    @Test
    public void usernameWithUnderscoreAndShorterIsValid() {
        assertTrue(PROG5121POEPART2.checkUserName("us_"));
        assertTrue(PROG5121POEPART2.checkUserName("_"));
    }

    @Test
    public void usernameWithoutUnderscoreIsInvalid() {
        assertFalse(PROG5121POEPART2.checkUserName("user"));
    }

    @Test
    public void usernameLongerThanFiveCharsIsInvalid() {
        assertFalse(PROG5121POEPART2.checkUserName("user_1"));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    public void passwordMeetingAllRulesIsValid() {
        assertTrue(PROG5121POEPART2.checkPasswordComplexity("Password5!"));
    }

    @Test
    public void passwordWithoutCapitalIsInvalid() {
        assertFalse(PROG5121POEPART2.checkPasswordComplexity("password5!"));
    }

    @Test
    public void passwordWithoutDigitIsInvalid() {
        assertFalse(PROG5121POEPART2.checkPasswordComplexity("Password!!"));
    }

    @Test
    public void passwordWithoutSpecialCharIsInvalid() {
        assertFalse(PROG56121PART2.checkPasswordComplexity("Password55"));
    }

    @Test
    public void passwordShorterThanEightIsInvalid() {
        assertFalse(PROG5121POEPART2.checkPasswordComplexity("Pa5!"));
    }

    // ---------- checkCellPhoneNumber ----------

    @Test
    public void validSouthAfricanNumberIsAccepted() {
        assertTrue(PROG5121POEPART2.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void numberWithoutInternationalCodeIsRejected() {
        assertFalse(PROG5121POEPART2.checkCellPhoneNumber("0123456789"));
    }

    @Test
    public void numberWithTooFewDigitsIsRejected() {
        assertFalse(PROG5121POEPART2.checkCellPhoneNumber("+2712345678"));
    }

    @Test
    public void numberWithTooManyDigitsIsRejected() {
        assertFalse(PROG5121POEPART2.checkCellPhoneNumber("+271234567890"));
    }

    @Test
    public void numberWithLettersIsRejected() {
        assertFalse(PROG5121POEPART2.checkCellPhoneNumber("+27abcdefghi"));
    }

    // ---------- registerUser(String, String) ----------

    @Test
    public void registerUserSucceedsWithValidCredentials() {
        assertEquals("Username and password successfully captured. User registered!",
                PROG5121POEPART2.registerUser("user_", "Password5!"));
    }

    @Test
    public void registerUserReportsBadUsername() {
        assertEquals("Username is not correct. Must contain _ and have a maximum of 5 characters.",
                PROG56121PART2.registerUser("username", "Password5!"));
    }

    @Test
    public void registerUserReportsBadPassword() {
        assertEquals("Password is not correct. Must have 8+ characters, a capital letter, a number, and a special character.",
                PROG56121PART2.registerUser("user_", "weak"));
    }

    // ---------- loginUser / returnLoginStatus ----------

    @Test
    public void loginSucceedsWithMatchingCredentials() {
        assertTrue(PROG56121PART2.loginUser("user_", "Password5!", "user_", "Password5!"));
    }

    @Test
    public void loginFailsWithWrongUsername() {
        assertFalse(PROG56121PART2.loginUser("other", "Password5!", "user_", "Password5!"));
    }

    @Test
    public void loginFailsWithWrongPassword() {
        assertFalse(PROG56121PART2.loginUser("user_", "Wrong5!!", "user_", "Password5!"));
    }

    @Test
    public void loginStatusMessagesMatchOutcome() {
        assertEquals("Login successful! Welcome back!", PROG56121PART2.returnLoginStatus(true));
        assertEquals("Username or password incorrect, please try again.",
                PROG56121PART2.returnLoginStatus(false));
    }

    // ---------- validateNumber ----------

    @Test
    public void validateNumberAcceptsCorrectNumber() {
        assertEquals("Recipient number entered successfully.",
                PROG56121PART2.validateNumber("+27123456789"));
    }

    @Test
    public void validateNumberRejectsNull() {
        assertEquals("Recipient number must start with +27.",
                PROG56121PART2.validateNumber(null));
    }

    @Test
    public void validateNumberRejectsMissingPrefix() {
        assertEquals("Recipient number must start with +27.",
                PROG56121PART2.validateNumber("0821234567"));
    }

    @Test
    public void validateNumberRejectsWrongLength() {
        assertEquals("Recipient number must contain +27 followed by 9 digits.",
                PROG56121PART2.validateNumber("+2712"));
    }

    // ---------- createMessageHash ----------

    @Test
    public void hashUsesFirstAndLastWordInUpperCase() {
        assertEquals("00:1:HITONIGHT",
                PROG56121PART2.createMessageHash("0012345678", 1, "Hi tonight"));
    }

    @Test
    public void hashOfSingleWordRepeatsTheWord() {
        assertEquals("00:2:HELLOHELLO",
                PROG56121PART2.createMessageHash("0012345678", 2, "Hello"));
    }

    @Test
    public void hashIgnoresMiddleWordsAndExtraSpaces() {
        assertEquals("12:3:STARTEND",
                PROG56121PART2.createMessageHash("1299999999", 3, "  start   middle words   end  "));
    }

    @Test
    public void hashOfEmptyMessageUsesEmptyMarker() {
        assertEquals("00:1:EMPTY",
                PROG56121PART2.createMessageHash("0012345678", 1, "   "));
    }

    // ---------- escapeJson ----------

    @Test
    public void escapeJsonEscapesQuotesAndBackslashes() {
        assertEquals("say \\\"hi\\\" C:\\\\temp",
                PROG56121PART2.escapeJson("say \"hi\" C:\\temp"));
    }

    @Test
    public void escapeJsonEscapesControlCharacters() {
        assertEquals("a\\nb\\rc\\td", PROG56121PART2.escapeJson("a\nb\rc\td"));
    }

    @Test
    public void escapeJsonLeavesPlainTextUnchanged() {
        assertEquals("plain text 123", PROG56121PART2.escapeJson("plain text 123"));
    }

    // ---------- discardLastMessage (shared state) ----------

    @Test
    public void discardLastMessageRemovesNewestAndDecrementsCount() {
        PROG56121PART2.messages.clear();
        PROG56121PART2.sentCount = 0;

        PROG56121PART2.messages.add(new Message("1", "H1", "+27123456789", "first", "t1", "SENT"));
        PROG56121PART2.messages.add(new Message("2", "H2", "+27123456789", "second", "t2", "SENT"));
        PROG56121PART2.sentCount = 2;

        PROG56121PART2.discardLastMessage();

        assertEquals(1, PROG56121PART2.messages.size());
        assertEquals("first", PROG56121PART2.messages.get(0).content);
        assertEquals(1, PROG56121PART2.sentCount);

        PROG56121PART2.messages.clear();
        PROG56121PART2.sentCount = 0;
    }

    @Test
    public void discardLastMessageOnEmptyListDoesNothing() {
        PROG56121PART2.messages.clear();
        PROG56121PART2.sentCount = 0;

        PROG56121PART2.discardLastMessage();

        assertTrue(PROG56121PART2.messages.isEmpty());
        assertEquals(0, PROG56121PART2.sentCount);
    }

    private static class Message {

        public Message(String string, String h1, String string0, String first, String t1, String sent) {
        }
    }
}