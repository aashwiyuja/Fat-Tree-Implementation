# Fat Tree Implementation

This Java program implements a Fat Tree network topology, providing functionalities to calculate distances between nodes and display various node IDs based on the input parameter 'k'.

## Usage

1. Compile and run the program.
2. Enter a valid even number for 'k' when prompted.
3. Choose options from the menu:
   - **(1):** Find distance between nodes.
   - **(2):** Enter a different value for 'k'.
   - **(0):** Exit the program.

## Input

The program prompts the user to enter the parameter 'k' representing the Fat Tree network's scale.

Example Input:
```plaintext
Enter input k:
4
```

## Output

The program outputs various IDs for POD, PM, Edge, Aggregation, and Core based on the provided 'k'. It also calculates and displays the distance between specified source and destination nodes.

Example Output:
```plaintext
############## MENU ##############
Please enter:
(1): To find distance for k, 4
(2): To enter a different value for k
(0): To Exit
##################################
1
Enter source node Id:
3
Enter destination node Id:
11
-------------------------------
The distance from 3 to 11 is: 2
-------------------------------
```

## Notes

- The program validates the input for 'k' to be a valid even number.
- For 'k' equal to 2, default values are displayed.
- The program uses mathematical calculations to determine node IDs and distances in a Fat Tree topology.
