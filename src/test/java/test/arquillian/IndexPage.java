/*
 * File created on Apr 7, 2014 
 *
 * Copyright 2008-2013 Virginia Polytechnic Institute and State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package test.arquillian;

import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * DESCRIBE THE TYPE HERE.
 *
 * @author Michael Irwin
 */
@Location("index.xhtml")
public class IndexPage {

  @FindBy(tagName = "title")
  private WebElement pageTitle;
  
  public void assertOnIndexPage() {
    assertTrue(pageTitle.getText().contains("Hello world"));
  }
  
}
