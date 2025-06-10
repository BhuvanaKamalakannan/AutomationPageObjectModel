package com.planittesting.utilities;

public class TestUtil  {
    public static Number parseNumber(String num) {
        if (num != null) {
            try {
                return Double.parseDouble(num);
            } catch (NumberFormatException ex) {
                Log.error("!!!!!!!!!!!!! Number format exception:" + ex);
            }
            catch(Exception ex){
                Log.error("!!!!!!!!!!!!! Exception in parseNumber method:" + ex);
            }
        }
        return null;
    }
}
