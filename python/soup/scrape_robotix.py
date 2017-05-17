# scrape_robotix.py
# 2017-may-16, 2573098.inc

import requests
from bs4 import BeautifulSoup

testnum = 2
if testnum==1:
    url = ('http://www.canadarobotix.com/robot-parts' +
           '/microcontrollers/arduino-microcontroller')
    response = requests.get(url)
    page = response.content
else:
    with open('Robotix.html', 'r') as f:
        page = f.read()
        f.closed
#
soup = BeautifulSoup(page, 'lxml')

print soup.title.name
idx=0
for item in soup.find_all('div', class_="name"):
    print("product: " + item.string[:55])
    m = soup.select("div.model")
    print("model:   " + m[idx].string)
    c1 = soup.select("div.price")
    c2 = c1[idx].string.strip()
    print("price:   " + c2)
    idx += 1

## scrape_robotix.py
