from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

# Set the custom download directory
download_dir = r"F:\Chaitu\MyStudy\German\Learn_German\Docs"  # Replace with your desired path

# Initialize ChromeOptions
chrome_options = Options()

# Set preferences for Chrome
chrome_prefs = {
    "download.default_directory": download_dir,  # Set custom download path
    "download.prompt_for_download": False,       # Disable download prompt
    "download.directory_upgrade": True,          # Automatically overwrite the download directory
    "safebrowsing.enabled": True                 # Enable safe browsing for downloads
}
chrome_options.add_experimental_option("prefs", chrome_prefs)

# Initialize the WebDriver (using Chrome in this example)
driver = webdriver.Chrome(options=chrome_options)  # or use webdriver.Firefox() for Firefox
driver.maximize_window()

# Open a webpage
driver.get("https://learngerman.arofinity.de/wiscode/user/app/1/container/1/Membership/1/group/0/type/2/view/1")  # Replace with the actual URL

# Example: Click on elements by their XPath or other locators
try:
    # Wait for the page to load properly (can be enhanced with WebDriverWait)
    time.sleep(3)

    #Login
    u_name_el = driver.find_element(by=By.XPATH, value='//input[@name="username"]')
    u_name_el.clear()
    u_name_el.send_keys('tchaitu.jkc@gmail.com')

    pwd_el = driver.find_element(By.XPATH, '//input[@name="password"]')
    pwd_el.clear()
    pwd_el.send_keys("He!!olg@123")

    driver.find_element(By.XPATH, '//button[@type="submit"]').click()
    time.sleep(5)

    for i in range(80,201):
        driver.find_element(By.XPATH, f'//td[text()="Worksheet {i}"]').click()
        time.sleep(1)
        driver.find_element(By.XPATH, '//div[@class="rightDiv"]/div[@id="mySidenav"]/div/div/div[4]/div/div[2]/div/div/table/thead/tr/th/div/div/input[@aria-label="Select all checkbox"]/following-sibling::span').click()
        driver.find_element(By.XPATH, '//button[@aria-label="download"]').click()

    
    # # Example 1: Click on element with specific XPath
    # element1 = driver.findElement(By.XPATH, "//button[text()='Click Me']")  # Replace with your XPath
    # element1.click()

    # # Example 2: Click on element with CSS selector
    # element2 = driver.find_element(By.CSS_SELECTOR, ".example-class")  # Replace with your CSS selector
    # element2.click()

    # # Example 3: Click on element by ID
    # element3 = driver.find_element(By.ID, "submit-button")  # Replace with your element's ID
    # element3.click()

    # # You can also interact with elements in a loop if needed (e.g., click on multiple similar elements)
    # elements = driver.find_elements(By.CLASS_NAME, "clickable-item")  # Example of multiple elements
    # for element in elements:
    #     element.click()
    #     time.sleep(1)  # Small delay between clicks
finally:
    # Close the browser after all actions
    time.sleep(5)  # Optional delay to visually inspect the result before closing
    driver.quit()
