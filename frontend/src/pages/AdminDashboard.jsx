// AdminDashboard.jsx
// Admin home with stats & links
import { useState } from "react";
import AdminSideBar from "../components/admin/AdminSideBar";
import TeacherList from "../components/admin/TeacherList";
import StudentList from "../components/admin/StudentList";
import CourseList from "../components/admin/CourseList";


const AdminDashboard = () => {

  const [currentPage, setCurrentPage] = useState('Teachers');

  let content;
  switch (currentPage) {
    case 'Teachers':
      content = <TeacherList />;
      break;
    case 'Students':
      content = <StudentList />;
      break;
    case 'Courses':
      content = <CourseList />;
      break;
    default:
      content = 'Nothing pressed'
      break;
  }

  return (
    <div className='text-base-text-color h-dvh flex min-w-fit font-[chivo]'>

      {/* SideBar */}
      <AdminSideBar setCurrentPage={setCurrentPage} currentPage={currentPage} />

      {/* Main Window */}

      {/* Top Bar */}

      <div className="flex flex-col px-10 pt-4 w-full">
        
        <div className="flex items-center justify-between">
          
          <h1 className='flex h-1/12 text-5xl items-center p-2 font-[DynaPuff]'>
            <img src="/iadst-favicon-1.png" className="w-20" />
            IADST
          </h1>

          <span className="text-3xl">{currentPage}</span>

        </div>

        {/* Content Wrapper */}
        <div className='content h-11/12'>{content}</div>

      </div>

    </div >
  );
};




export default AdminDashboard;
