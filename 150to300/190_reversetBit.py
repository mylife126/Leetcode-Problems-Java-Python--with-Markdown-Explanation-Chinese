'''
1 byte = 8bits
4 byte = 32bits
'''

class Solution:
    # @param n, an integer
    # @return an integer
    def reverseBits(self, n):
        #bin(n) would return on101000xxxxxx, thus we want to slice out the 0b
        #zfill would fill 0 infront of the string to make the string to be 32 in length
        #then we reverse the whole string
        #and then add"0b" to the string
        #finally we convert the bin back to int
        return int("0b" + bin(n)[2:].zfill(32)[::-1], 2)