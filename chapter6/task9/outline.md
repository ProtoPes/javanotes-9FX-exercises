# Logic

- We are saving coordinates of user clicks and draw a polygon when user click around first click

- We can use utility method to draw a figure

- On canvas click:
    if no coordinates stored or first coordinates not equal last click:
        store coordinates
    else
        draw a figure
        erase data

- For checking if coordinates exist we can initialize arrays with all variables equal to -1
or we can use `ArrayList` and do check for `null`

# Variables

- canvas
- two arrays of double for x and y coordinates
