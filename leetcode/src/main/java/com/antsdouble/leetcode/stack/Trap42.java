package com.antsdouble.leetcode.stack;

import java.util.Stack;

/**
 * @Author lyy
 * @Description 42
 * @Date 2020-02-05
 **/
public class Trap42 {
	public static void main(String[] args) {
		int trap = trap6(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
		System.out.println(trap);
	}
	public static int trap6(int[] height) {
		int sum = 0;
		Stack<Integer> stack = new Stack<>();
		int current = 0;
		while (current < height.length) {
			//如果栈不空并且当前指向的高度大于栈顶高度就一直循环
			while (!stack.empty() && height[current] > height[stack.peek()]) {
				int h = height[stack.peek()]; //取出要出栈的元素
				stack.pop(); //出栈
				if (stack.empty()) { // 栈空就出去
					break;
				}
				int distance = current - stack.peek() - 1; //两堵墙之前的距离。
				int min = Math.min(height[stack.peek()], height[current]);
				sum = sum + distance * (min - h);
			}
			stack.push(current); //当前指向的墙入栈
			current++; //指针后移
		}
		return sum;
	}
	public  static int trap(int[] height) {

		if (height==null){
			return 0;
		}
		int sum=0;
		Stack<Integer> stack=new Stack<Integer>();
		int maxIndex=0;
		for (int i=0;i<height.length;i++){
			if (stack.isEmpty()&&height[i]<=0){
				maxIndex=i;
				continue;
			}
			stack.push(height[i]);
			if (height[i]>=height[maxIndex]){
				// 计算
				sum+= algoStack(stack);
				stack.push(height[i]);
				maxIndex=i;
			}
		}
		return sum;
	}


	public static  int algoStack(Stack<Integer> stack){

		Integer num = stack.firstElement() * (Integer)stack.size()+
		(stack.lastElement()-stack.firstElement());
		while (!stack.isEmpty()){
			num= num - stack.pop();
		}
		return num;
	}

}
