// AdminDashboard.jsx
// Admin home with stats & links
import AdminSideBar from "../components/admin/AdminSideBar";

const AdminDashboard = () => {


  return (
    <div className='text-light-lavender h-dvh flex'>
      
      <AdminSideBar />

      <div>

        <h1 className='flex h-1/12 text-5xl items-center pl-[5%]'>ADMIN</h1>

        <div className='content w-[88%]'></div>

      </div>

    </div >
    );
};




export default AdminDashboard;
