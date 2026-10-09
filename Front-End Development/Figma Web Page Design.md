# Complete Figma Web Page Design & Prototyping Guide

- **Topic:** Complete Figma Web Page Design & Prototyping Guide

- **Instructor: RAHUL KP**

## 1. Getting Started: Account Setup & File Creation

### Step 1: Log In to Figma

1. Open your web browser and go to [figma.com](https://www.figma.com).
2. Click **Log in** in the top right corner.
3. Sign in using your **Google Account** or enter your registered email address and password.

### Step 2: Create a New Design File

1. Once logged in, you will land on the Figma File Browser / Dashboard.
2. Click the blue **`+ Design file`** button in the top-right corner of the screen (or click **`Drafts`** on the left sidebar and select **`+ Design file`**).
3. Click on **`Untitled`** at the top left of the editor toolbar to rename your file (e.g., _My Web Page Design_).

---

## 2. Designing the Web Page Layout

### Step 1: Add a Frame and Layout Grid

1. Press **`F`** to open the Frame tool, then click **Desktop (1440px)** in the right-hand panel.
2. With the frame selected, click **`+`** next to **Layout Grid** in the right panel.
3. Change the grid type from _Grid_ to **Columns** and adjust the settings:
   - **Count:** `12`
   - **Margin:** `80px`
   - **Gutter:** `24px`

### Step 2: Build the Navigation Bar (80px height)

1. Press **`R`** (Rectangle tool) and draw a shape across the top: `1440px × 80px`.
2. Press **`T`** (Text tool) to add header elements:
   - **Logo:** `Bold, 20px` (aligned to the far left column).
   - **Nav Links:** _Features_, _Pricing_, _About_ (`Regular, 16px`, spaced 32px apart).
   - **CTA Button Frame:** Draw a `120px × 44px` frame, add text _Get Started_, pick a fill color, and set **Corner Radius: 8px**.

### Step 3: Design the Hero Section

1. Create a primary headline text box spanning 7–8 columns:
   - **Headline:** `Bold, 48px - 56px`, Auto Height.
   - **Subheading:** `Regular, 18px`, muted color (`#666666`).
2. Add primary and secondary CTA buttons beneath the text.
3. On the right-side columns (4–5 columns), draw a placeholder rectangle (`500px × 400px`) for a hero image or app preview.

### Step 4: Build a 3-Column Features Grid

1. Leave `100px - 120px` vertical space below the hero section.
2. Add a centered Section Title across the layout (`Bold, 36px`).
3. Across your 12-column grid, create 3 feature cards (each spanning 4 columns):
   - Draw a frame (`360px × 240px`).
   - Add an Icon placeholder (`32px × 32px`), Feature Title (`Bold, 20px`), and Description (`Regular, 15px`) inside each.

---

## 3. Creating Buttons & Interactive Prototyping

### Step 1: Duplicate the Frame for Page 2

1. Select your main canvas frame (`Desktop - 1`).
2. Press **`Cmd + D`** (Mac) or **`Ctrl + D`** (Windows) to duplicate it (or press **`F`** to create a new blank frame next to it).
3. Double-click the frame title at the top left of the canvas and rename it to **`Desktop - 2`**.

### Step 2: Create a Button Using Auto Layout

1. Press **`T`** and click inside **`Desktop - 1`** to type your button text (e.g., _Next Page_).
2. Press **`Esc`** to exit text-editing mode (ensure the text box has a solid blue bounding box instead of a blinking text cursor).
3. Wrap the text in Auto Layout using any of these 3 methods:
   - **Keyboard Shortcut:** Press **`Shift + A`**.
   - **Right-Click:** Right-click the text layer and choose **Add Auto Layout**.
   - **Right Panel:** Select the text layer, find the **Auto Layout** section in the right sidebar, and click **`+`**.
4. With the Auto Layout frame selected, add a background color under **Fill** and set **Corner Radius** to `8px`.

### Step 3: Link the Prototype Interaction

1. Switch to **Prototype** mode by clicking the **Prototype** tab at the top of the right panel.
2. Click directly on your newly created button inside **`Desktop - 1`**.
3. Hover over the right edge of the button until a small circular node (**`+`**) appears.
4. Click and drag the connector line from that node to **`Desktop - 2`**.
5. In the **Interaction Details** popup panel:
   - **Trigger:** `On click`
   - **Action:** `Navigate to`
   - **Destination:** `Desktop - 2`
   - **Animation:** `Instant` (or `Smart Animate` / `Dissolve`)

### Step 4: Test Your Navigation

1. Click the **Play** button (▶) in the top-right toolbar to open Presentation View.
2. Click your button in the preview window to confirm that it navigates smoothly to **`Desktop - 2`**.
