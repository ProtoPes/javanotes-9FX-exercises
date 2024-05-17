# Variables

## Global

- 1 `TextArea`
- 3 `Label` for outputting numbers

## Local

- `TilePane` with preferred columns equals 1 or `VBox`. Set background to blue.
Border gaps and spaces between children should be made, approximately 3 pixels.
- 1 `Button`
- 3 `HBox` to hold labels
- 3 `Label` to output text for numbers

# Logic

- Connect event listener on the button, after click `calculate` method will be called
and then `show` method.
- `calculate` will change global labels with updated numbers
- `show` used at the beginning and every time the button is clicked. It will call
`setText` methods on labels.

# Layout

`TextField` is large so I need to understand how to make it big

