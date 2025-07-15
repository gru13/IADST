// CourseList.jsx
// Table of courses and memberships
import React from "react";
import { FaSearch } from "react-icons/fa";
import { IoAddCircleOutline } from "react-icons/io5";

function CourseList() {
  return (
    <div className="flex flex-col h-full">
      <div className="flex text-3xl items-center justify-between flex-none mb-5">
        <button>
          <IoAddCircleOutline />
        </button>

        <div className="flex grow text-xl items-center border-1 border-accent-color-2 p-4 py-3 mx-3 rounded-2xl">
          <input
            type="text"
            placeholder="Search here!!"
            className="grow px-2 rounded-2xl outline-none text-3xl"
          />
          <button type="submit" className="flex items-center hover:scale-110">
            <FaSearch />
          </button>
        </div>
      </div>

      <div className="h-full flex flex-nowrap w-full overflow-x-scroll">
        <div className="h-11/12 min-w-11/12  rounded-2xl  border-2 m-2"></div>
        <div className="h-11/12 min-w-11/12  rounded-2xl border-2 m-2"></div>
        <div className="h-11/12 min-w-11/12  rounded-2xl border-2 m-2"></div>
        <div className="h-11/12 min-w-11/12  rounded-2xl border-2 m-2"></div>



      </div>
    </div>
  );
}

export default CourseList;
