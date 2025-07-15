import { useState } from "react";


import { PiChalkboardTeacher } from "react-icons/pi";
import { HiOutlineAcademicCap } from "react-icons/hi2";
import { FiUsers } from "react-icons/fi";
import { HiOutlineBars3 } from "react-icons/hi2";
import { IoClose } from "react-icons/io5";




function AdminSideBar({currentPage, setCurrentPage}) {
    const [sideBarToggle, setSideBarToggle] = useState(false);



    const buttonStyle = `basis-20 m-4 p-3 flex items-center  text-2xl  transform-all duration-400 ease-in-out hover:rounded-3xl hover:scale-110`;
    const spanClassStyle =`p-2 ${sideBarToggle ? 'w-[20%]' : 'hidden'}`
    return (
        <div className='sideBar flex flex-col  border-r-2 border-y-1 rounded-r-2xl mt-2 mb-1 border-accent-color-2'>

            <button className={`${buttonStyle} justify-center ${sideBarToggle ? 'rotate-360' : 'rotate-180'}`} onClick={() => setSideBarToggle(!sideBarToggle)}>
                {sideBarToggle ? <IoClose /> : <HiOutlineBars3 />}
            </button>

            <button title="Teachers" className={`${buttonStyle} ${currentPage === 'Teachers'?'text-highlight-text-color scale-130':''}`} onClick={()=>{setCurrentPage('Teachers')}}>
                <PiChalkboardTeacher/>
                <span className={spanClassStyle}>
                    Teacher
                </span>
            </button>
            
            <button title="Students" className={`${buttonStyle} ${currentPage === 'Students'?'text-highlight-text-color scale-130':''}`} onClick={()=>{setCurrentPage('Students')}}>
                <FiUsers />
                <span className={spanClassStyle}>
                    Students
                </span>
            </button>
            <button title="Courses" className={`${buttonStyle} ${currentPage === 'Courses'?'text-highlight-text-color scale-130':''}`} onClick={()=>{setCurrentPage('Courses')}}>
                <HiOutlineAcademicCap />
                <span className={spanClassStyle}>
                    Courses
                </span>
            </button>
        </div>
    );
}


export default AdminSideBar;