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

import static org.junit.Assert.*;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * A simple test
 *
 * @author Michael Irwin
 */
@RunWith(Arquillian.class)
public class ArquillianTest {
  
  /**
   * Create a full deployment for this war
   */
  @Deployment(testable = false, name = "test.war")
  public static WebArchive createFullDeployment() {
    
    // From GitHub documentation for the ShrinkWrap Resolver repository
    // https://github.com/shrinkwrap/resolver
    
    WebArchive archive = ShrinkWrap.create(MavenImporter.class, "test.war")
        .loadPomFromFile("pom.xml")
        .importBuildOutput()
        .as(WebArchive.class);
    
    System.out.println("=========== Archive name: " + archive.getName());
    return archive;
  }
  
  @ArquillianResource
  private URL deploymentUrl;
  
  @Drone
  private WebDriver browser;
  
  @Test
  public void testHelloWorld() {
    System.out.println("========== Base URL is: " + deploymentUrl);
    System.out.println("========== External Form is: " + deploymentUrl.toExternalForm());
    browser.get(deploymentUrl.toExternalForm() + "index.xhtml");
    System.out.println("========== Actual page title: " + browser.getTitle());
    assertTrue(browser.getTitle().contains("Hello world"));
  }
  
}
