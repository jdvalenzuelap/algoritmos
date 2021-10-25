def roman(number):
    romans = [[1,'I'], [4,'IV'], [5,'V'], [9,'IX'], [10,'X'], [40, 'XL'], [50,'L'], [90,'XC'], [100,'C'], [400,'CD'], [500,'D']]
    finalNumber = ''

    index = len(romans) - 1

    while number:
        division = number // romans[index][0]
        number %= romans[index][0]

        while division:
            finalNumber += romans[index][1]
            division -=1

        index -= 1
    
    return finalNumber



    