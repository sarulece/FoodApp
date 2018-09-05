import { AppPage } from './app.po';
import { browser, by, element } from 'protractor';
import { protractor } from 'protractor';

describe('Foodie App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Foodie App');
  });

  it('should be redirected to home(/) page upon launching the application', () =>
  {
    expect(browser.getCurrentUrl()).toContain('/');
  });

  it('Home page should contain 20 cards', () =>
  {
     const searchItems = element.all(by.css(".restaurant-name"));
     expect(searchItems.count()).toBe(20);
  });

  it('should be redirected to /search page upon clicking the search button', () =>
  {
    element(by.cssContainingText('button', 'Search')).click();
    expect(browser.getCurrentUrl()).toContain('/search');
  });

  it('should be able to search restaurants in search page', () => 
  {
    element(by.id('location')).sendKeys("chennai");
    element(by.id('cuisine')).sendKeys("barbecue");
    element(by.id('search-button')).click();
    const searchItems = element.all(by.css(".restaurant-name"));
    expect(searchItems.count()).toBe(20);
    expect(searchItems.get(0).getText()).toContain('Coal Barbecues');
  });

  it('should be able to add restaurant to favourites', async() =>
  {
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    const addButtons = element.all(by.cssContainingText('button', 'Add favourite'));
    browser.actions().mouseMove(addButtons.get(0)).click().perform();
    const removeButtons = element.all(by.cssContainingText('button', 'Remove'));
    expect(removeButtons.count()).toBeGreaterThanOrEqual(1);
  });

  it('should be redirected to /favourites page upon clicking the Favourites button', () =>
  {
    const favouritesButton = element(by.cssContainingText('button', 'Favourites'));
    browser.actions().mouseMove(favouritesButton).click().perform();
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/favourites');
  });

  it('should be redirected to /restaurant-detail page upon clicking the restaurant card title', () =>
  {
    element(by.css('.restaurant-name')).click();
    browser.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/restaurant-detail');
  });

});
