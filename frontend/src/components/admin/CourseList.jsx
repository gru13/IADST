// CourseList.jsx
// Table of courses and memberships
import React from 'react';
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";


function CourseList() {
    return (
    <div className="flex flex-col h-full max-w-full">

      <div className='flex text-3xl items-center justify-between flex-none' >

        <button><IoAddCircleOutline /></button>

        <div className='flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl'>
          <input type="text" placeholder='Search here!!' className='grow px-2 rounded-2xl outline-none text-3xl' />
          <button type="submit" className='flex items-center hover:scale-110'>
            <FaSearch />
          </button>
        </div>

      </div>
    </div>
  );
}

export default CourseList;
