from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import ElementClickInterceptedException
import time

# Set the custom download directory
download_dir = r"F:\Chaitu\MyStudy\German\Learn_German\Docs\Lession_Transcripts"  # Replace with your desired path

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
driver.get("https://learngerman.arofinity.de/wiscode/user/app/1/container/1/Membership/1/group/0/type/4/view/76")  # Replace with the actual URL

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

    els = driver.find_elements(By.XPATH, '//table[@id="viewGrid_content_table"]/tbody/tr')
    for e in els:
        try:
            e.click()
            time.sleep(2)
            driver.find_element(By.XPATH, '//div[@class="rightDiv"]/div[@id="mySidenav"]/div/div/div[3]/div/div[2]/div/div/table/thead/tr/th/div/div/input[@aria-label="Select all checkbox"]/following-sibling::span').click()
            driver.find_element(By.XPATH, '//button[@aria-label="download"]').click()
        except ElementClickInterceptedException as e:
            print(f"Skipping element: {e}")

finally:
    # Close the browser after all actions
    time.sleep(5)  # Optional delay to visually inspect the result before closing
    driver.quit()
