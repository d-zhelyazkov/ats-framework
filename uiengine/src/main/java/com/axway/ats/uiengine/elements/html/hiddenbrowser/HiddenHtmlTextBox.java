/*
 * Copyright 2017 Axway Software
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.axway.ats.uiengine.elements.html.hiddenbrowser;

import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import com.axway.ats.common.PublicAtsApi;
import com.axway.ats.uiengine.UiDriver;
import com.axway.ats.uiengine.elements.UiElementProperties;
import com.axway.ats.uiengine.elements.html.HtmlElementLocatorBuilder;
import com.axway.ats.uiengine.elements.html.HtmlTextBox;
import com.axway.ats.uiengine.exceptions.VerifyEqualityException;
import com.axway.ats.uiengine.exceptions.VerifyNotEqualityException;
import com.axway.ats.uiengine.utilities.UiEngineUtilities;
import com.axway.ats.uiengine.utilities.hiddenbrowser.HiddenHtmlElementState;

/**
 * An HTML Text Box
 * @see HiddenHtmlElement
 */
@PublicAtsApi
public class HiddenHtmlTextBox extends HtmlTextBox {

    public HiddenHtmlTextBox( UiDriver uiDriver,
                              UiElementProperties properties ) {

        super( uiDriver, properties );
        String matchingRules[] = properties.checkTypeAndRules( this.getClass().getSimpleName(),
                                                               "HiddenHtml",
                                                               HiddenHtmlElement.RULES_DUMMY );

        // generate the XPath of this HTML element
        String xpath = HtmlElementLocatorBuilder.buildXpathLocator( matchingRules,
                                                                    properties,
                                                                    new String[]{ "text",
                                                                                  "password",
                                                                                  "hidden",
                                                                                  "search",
                                                                                  "email",
                                                                                  "url",
                                                                                  "tel" },
                                                                    "input" );
        properties.addInternalProperty( HtmlElementLocatorBuilder.PROPERTY_ELEMENT_LOCATOR, xpath );
    }

    /**
     * Get the Text Box value
     * @return
     */
    @Override
    @PublicAtsApi
    public String getValue() {

        new HiddenHtmlElementState( this ).waitToBecomeExisting();

        return HiddenHtmlElementLocator.findElement( this ).getAttribute( "value" );
    }

    /**
     * Append text to the current content of a Text Box
     * 
     * @param value
     */
    @PublicAtsApi
    public void appendValue(
                             String value ) {

        new HiddenHtmlElementState( this ).waitToBecomeExisting();

        HtmlUnitWebElement element = HiddenHtmlElementLocator.findElement( this );
        element.sendKeys( value );
        
        UiEngineUtilities.sleep();
    }

    /**
     * Set the Text Box value
     *
     * @param value
     */
    @Override
    @PublicAtsApi
    public void setValue(
                          String value ) {

        new HiddenHtmlElementState( this ).waitToBecomeExisting();

        HtmlUnitWebElement element = HiddenHtmlElementLocator.findElement( this );
        element.clear();
        element.sendKeys( value );
        
        UiEngineUtilities.sleep();
    }

    /**
     * Verify the Text Box value is as specified
     *
     * @param expectedValue
     */
    @Override
    @PublicAtsApi
    public void verifyValue(
                             String expectedValue ) {

        expectedValue = expectedValue.trim();
        String actualText = getValue().trim();
        if( !actualText.equals( expectedValue ) ) {
            throw new VerifyEqualityException( expectedValue, actualText, this );
        }
    }

    /**
     * Verify the Text Box value is NOT as specified
     *
     * @param notExpectedValue
     */
    @Override
    @PublicAtsApi
    public void verifyNotValue(
                                String notExpectedValue ) {

        String actualText = getValue();
        if( actualText.equals( notExpectedValue ) ) {
            throw new VerifyNotEqualityException( notExpectedValue, this );
        }
    }

}
